import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Teste 1
        testarGrafo("Teste 1", "C", "B", "A", "1", "2", "3");

        // Teste 2
        testarGrafo("Teste 2", "y", "z", "x", "4", "5", "6");

        // Teste 3
        testarGrafo("Teste 3", "M", "N", "O", "7", "8", "9");
    }

    private static void testarGrafo(String nomeTeste, String vertice1, String vertice2, String vertice3,
                                    String peso1, String peso2, String peso3) {
        Grafo grafo = new Grafo();

        Vertice v1 = new Vertice(vertice1);
        Vertice v2 = new Vertice(vertice2);
        Vertice v3 = new Vertice(vertice3);

        grafo.inserirVertice(v1);
        grafo.inserirVertice(v2);
        grafo.inserirVertice(v3);

        Aresta aresta1 = new Aresta(v1, v2, peso1);
        Aresta aresta2 = new Aresta(v2, v3, peso2);
        Aresta aresta3 = new Aresta(v3, v1, peso3);

        grafo.inserirAresta(aresta1);
        grafo.inserirAresta(aresta2);
        grafo.inserirAresta(aresta3);

        // Certifique-se de que o grafo é conexo antes de chamar twiceAround
        if (isConexo(grafo)) {
            List<Aresta> caminho = TwiceAroundTSP.twiceAround(grafo);

            System.out.println(nomeTeste + " - Caminho Final:");
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
        } else {
            System.out.println(nomeTeste + " - Grafo não é conexo.");
        }

        System.out.println();  // Linha em branco para separar os resultados dos testes
    }

    private static boolean isConexo(Grafo grafo) {
        Collection<Vertice> vertices = grafo.vertices();

        if (vertices.isEmpty()) {
            // Grafo vazio é considerado conexo
            return true;
        }

        Set<Vertice> visitados = new HashSet<>();
        dfs(vertices.iterator().next(), visitados, grafo);

        // Verifica se todos os vértices foram alcançados
        return visitados.size() == vertices.size();
    }

    private static void dfs(Vertice atual, Set<Vertice> visitados, Grafo grafo) {
        visitados.add(atual);
        for (Aresta aresta : grafo.arestasIncidentes(atual)) {
            Vertice vizinho = grafo.oposto(atual, aresta);
            if (!visitados.contains(vizinho)) {
                dfs(vizinho, visitados, grafo);
            }
        }
    }

}
