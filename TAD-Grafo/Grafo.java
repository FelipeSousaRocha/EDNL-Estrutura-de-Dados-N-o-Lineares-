import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Grafo {
    private List<Vertice> vertices; // Lista de vertices
    private List<Aresta> arestas; // Lista de arestas

    public Grafo() {
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
    }

    // Métodos para adicionar vértices e arestas ao Grafo por meio de listas
    public void inserirVertice(Vertice vertice) {
        if (vertices.contains(vertice)) {
            throw new IllegalArgumentException("Vértice já existe no grafo");
        }
        vertices.add(vertice);
    }

    public void inserirAresta(Aresta aresta) {
        if (!vertices.contains(aresta.getOrigem()) || !vertices.contains(aresta.getDestino())) {
            throw new IllegalArgumentException("Aresta contém vértices não presentes no grafo");
        }

        if (arestas.contains(aresta)) {
            throw new IllegalArgumentException("Aresta já existe no grafo");
        }

        arestas.add(aresta);

        // Adiciona a aresta à lista de vizinhos dos vértices
        aresta.getOrigem().adicionarVizinho(aresta);
        aresta.getDestino().adicionarVizinho(aresta);
    }


    // Método para verificar se uma aresta é direcionada
    public boolean éDirecionada(Aresta aresta) {
        return aresta.eDirecionada();
    }

    // Método para inserir uma aresta direcionada entre dois vértices
    public Aresta inserirArestaDirecionada(Vertice verticeOrigem, Vertice verticeDestino, String valor) {
        Aresta arestaDirecionada = new Aresta(verticeOrigem, verticeDestino, valor, true);
        arestas.add(arestaDirecionada);
        return arestaDirecionada;
    }

    // Método para verificar a existência de um vértice no Grafo
    public boolean contemVertice(Vertice vertice) {
        return vertices.contains(vertice);
    }

    // Método para verificar a existência de uma aresta no Grafo
    public boolean contemAresta(Aresta aresta) {
        return arestas.contains(aresta);
    }

    // Método para Obter a Lista de Vizinhos de um Vértice
    public List<Vertice> obterVizinhos(Vertice vertice) {
        return vertice.getVizinhos().stream()
                .map(aresta -> aresta.getOposto(vertice))
                .collect(Collectors.toList());
    }

    // Método para obter arestas incidentes a um vértice
    public List<Aresta> arestasIncidentes(Vertice vertice) {
        return vertice.getVizinhos().stream()
                .filter(aresta -> aresta.getOrigem().equals(vertice) || aresta.getDestino().equals(vertice))
                .collect(Collectors.toList());
    }

    // Método para obter todos os vértices no grafo
    public Collection<Vertice> vertices() {
        return vertices;
    }

    // Método para obter todas as arestas no grafo
    public Collection<Aresta> arestas() {
        return arestas;
    }

    // Método para Obter o Grau de um Vértice
    public int grauDoVertice(Vertice vertice) {
        return vertice.getVizinhos().size();
    }

    // Método para Remover um Vértice do Grafo
    public void removerVertice(Vertice vertice) {
        if (!vertices.contains(vertice)) {
            throw new IllegalArgumentException("Vértice não encontrado no grafo");
        }
        vertices.remove(vertice);
        arestas.removeIf(aresta -> aresta.getOrigem() == vertice || aresta.getDestino() == vertice);
    }

    // Metodo para remover Aresta do Grafo
    public void removerAresta(Aresta aresta) {
        if (!arestas.contains(aresta)) {
            throw new IllegalArgumentException("Aresta não encontrada no grafo");
        }
        arestas.remove(aresta);
    }


    // Retornar vertices finais de uma Aresta
    public List<Vertice> finalVertices(Aresta aresta) {
        return List.of(aresta.getOrigem(), aresta.getDestino());
    }

    // Vertice oposto
    public Vertice oposto(Vertice v, Aresta e) {
        if (e.getOrigem() == v) {
            return e.getDestino();
        } else if (e.getDestino() == v) {
            return e.getOrigem();
        } else {
            // Tratar o caso em que o vértice v não é incidente à aresta e
            return null;
        }
    }

    // Saber se e adjacente
    public boolean éAdjacente(Vertice v, Vertice v2) {
        return v.getVizinhos().stream()
                .anyMatch(aresta -> aresta.getDestino().equals(v2));
    }

    // Metodos para substituir vertice e aresta
    public void substituir(Vertice v, String x) {
        v.setValor(x);
    }

    public void substituir(Aresta e, String x) {
        e.setValor(x);
    }


}
