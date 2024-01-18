import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgorithm {

    public static List<Aresta> kruskal(Grafo grafo) {
        List<Aresta> agm = new ArrayList<>();
        List<Aresta> todasArestas = new ArrayList<>(grafo.arestas());

        // Ordenar todas as arestas em ordem crescente de peso
        Collections.sort(todasArestas, (a1, a2) -> Integer.compare(Integer.parseInt(a1.getValor()), Integer.parseInt(a2.getValor())));

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

}