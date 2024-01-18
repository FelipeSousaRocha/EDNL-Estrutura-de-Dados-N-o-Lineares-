import java.util.*;
import java.util.stream.Collectors;

public class TwiceAroundTSP {

    public static List<Aresta> twiceAround(Grafo grafo) {
        // Passo 1: Calcular a Árvore Geradora Mínima (AGM) usando Kruskal
        List<Aresta> agm = KruskalAlgorithm.kruskal(grafo);

        // Passo 2: Duplicar as arestas da AGM
        List<Aresta> arestasDuplicadas = duplicateEdges(agm);

        // Passo 3: Encontrar um ciclo euleriano no grafo com arestas duplicadas
        List<Vertice> cicloEuleriano = fleury(arestasDuplicadas);

        // Passo 4: Eliminar vértices duplicados do ciclo euleriano
        List<Aresta> caminhoFinal = removeDuplicates(cicloEuleriano);

        return caminhoFinal;
    }

    // Implementação do algoritmo de Kruskal para encontrar a AGM
    public static List<Aresta> kruskal(Grafo grafo) {
        List<Aresta> agm = new ArrayList<>();
        List<Aresta> todasArestas = new ArrayList<>(grafo.arestas());

        // Ordenar todas as arestas em ordem crescente de peso
        Collections.sort(todasArestas, Comparator.comparingInt(a -> Integer.parseInt(a.getValor())));

        // Inicializar conjunto disjunto
        ConjuntoDisjunto conjuntoDisjunto = new ConjuntoDisjunto();
        for (Vertice vertice : grafo.vertices()) {
            conjuntoDisjunto.makeSet(vertice);
        }

        // Adicionar arestas à AGM, evitando ciclos
        for (Aresta aresta : todasArestas) {
            Vertice origem = aresta.getOrigem();
            Vertice destino = aresta.getDestino();

            // Verificar se a adição da aresta cria um ciclo
            if (!conjuntoDisjunto.encontrar(origem).equals(conjuntoDisjunto.encontrar(destino))) {
                // Aresta não forma ciclo, adicionar à AGM
                agm.add(aresta);
                // Unir os conjuntos dos vértices conectados pela aresta
                conjuntoDisjunto.union(origem, destino);
            }
        }

        return agm;
    }

    // Função para duplicar as arestas da AGM
    private static List<Aresta> duplicateEdges(List<Aresta> agm) {
        List<Aresta> duplicadas = new ArrayList<>();
        for (Aresta aresta : agm) {
            duplicadas.add(new Aresta(aresta.getOrigem(), aresta.getDestino(), aresta.getValor()));
            duplicadas.add(new Aresta(aresta.getDestino(), aresta.getOrigem(), aresta.getValor()));
        }
        return duplicadas;
    }

    private static List<Vertice> fleury(List<Aresta> arestas) {
        // Verificar se o grafo é conexo
        if (!isConexo(arestas)) {
            System.out.println("O grafo não é conexo. Não é possível encontrar um ciclo euleriano.");
            return null;
        }

        // Criar um mapa para armazenar o grau de cada vértice
        Map<Vertice, Integer> graus = new HashMap<>();
        for (Aresta aresta : arestas) {
            graus.put(aresta.getOrigem(), graus.getOrDefault(aresta.getOrigem(), 0) + 1);
            graus.put(aresta.getDestino(), graus.getOrDefault(aresta.getDestino(), 0) + 1);
        }

        // Escolher um vértice inicial
        Vertice verticeInicial = arestas.get(0).getOrigem();
        for (Map.Entry<Vertice, Integer> entry : graus.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                verticeInicial = entry.getKey();
                break;
            }
        }

        // Inicializar pilha para armazenar o caminho
        Stack<Vertice> pilha = new Stack<>();
        List<Vertice> ciclo = new ArrayList<>();

        // Iniciar o caminho a partir do vértice inicial
        pilha.push(verticeInicial);

        while (!pilha.isEmpty()) {
            Vertice atual = pilha.peek();

            // Encontrar uma aresta não utilizada
            Aresta aresta = findUnusedEdge(atual, arestas);

            if (aresta != null) {
                // Adicionar a aresta ao caminho
                aresta.setUsada(true);
                pilha.push(aresta.getDestino());
            } else {
                // Remover o vértice da pilha se não houver mais arestas disponíveis
                pilha.pop();
                ciclo.add(atual);

                // Imprimir informações para depuração
                System.out.println("Ciclo parcial: " + ciclo.stream().map(Vertice::getValor).collect(Collectors.toList()));
            }
        }

        // Retornar o ciclo euleriano
        return ciclo;
    }


    // Encontra uma aresta não utilizada a partir de um vértice
    private static Aresta findUnusedEdge(Vertice vertice, List<Aresta> arestas) {
        for (Aresta aresta : arestas) {
            if (aresta.getOrigem().equals(vertice) && !aresta.isUsada()) {
                aresta.reset(); // Marcar a aresta como não usada
                return aresta;
            }
        }
        return null;
    }

    // Verifica se o grafo é conexo
    private static boolean isConexo(List<Aresta> arestas) {
        Set<Vertice> visitados = new HashSet<>();
        dfs(arestas.get(0).getOrigem(), visitados, arestas);
        return visitados.size() == arestas.stream().map(a -> a.getOrigem()).distinct().count();
    }

    // Implementação do algoritmo de busca em profundidade (DFS)
    private static void dfs(Vertice atual, Set<Vertice> visitados, List<Aresta> arestas) {
        visitados.add(atual);
        for (Aresta aresta : atual.getVizinhos()) {
            if (!visitados.contains(aresta.getDestino()) && !aresta.isUsada()) {
                dfs(aresta.getDestino(), visitados, arestas);
            }
        }
    }


    // Remove vértices duplicados do ciclo euleriano
    private static List<Aresta> removeDuplicates(List<Vertice> cicloEuleriano) {
        Set<Vertice> set = new LinkedHashSet<>(cicloEuleriano);
        List<Aresta> caminhoFinal = new ArrayList<>();
        Vertice[] vertices = set.toArray(new Vertice[0]);

        for (int i = 0; i < vertices.length - 1; i++) {
            Aresta aresta = findEdge(vertices[i], vertices[i + 1]);
            caminhoFinal.add(aresta);
        }

        return caminhoFinal;
    }

    // Encontra a aresta entre dois vértices
    private static Aresta findEdge(Vertice origem, Vertice destino) {
        for (Aresta aresta : origem.getVizinhos()) {
            if (aresta.getDestino().equals(destino)) {
                return aresta;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Criar um grafo com vértices e arestas (use a sua classe Grafo)
        Grafo grafo = new Grafo();

        // Executar o algoritmo Twice-Around
        List<Aresta> caminho = twiceAround(grafo);

        // Imprimir o caminho final
        System.out.println("Caminho Final:");
        if (caminho != null) {
            for (Aresta aresta : caminho) {
                if (aresta != null && aresta.getOrigem() != null) {
                    System.out.print(aresta.getOrigem().getValor() + " -> ");
                }
            }
            if (!caminho.isEmpty() && caminho.get(caminho.size() - 1) != null && caminho.get(caminho.size() - 1).getDestino() != null) {
                System.out.println(caminho.get(caminho.size() - 1).getDestino().getValor());
            } else {
                System.out.println("Caminho final vazio ou inválido.");
            }
        } else {
            System.out.println("O algoritmo não retornou um caminho válido.");
        }

    }
}