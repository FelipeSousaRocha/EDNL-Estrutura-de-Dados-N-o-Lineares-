public class Main {
    public static void main(String[] args) {
        // Criando um objeto da classe Grafo
        Grafo grafo = new Grafo();

        // Criando vértices
        Vertice v1 = new Vertice("A");
        Vertice v2 = new Vertice("B");
        Vertice v3 = new Vertice("C");

        // Criando arestas
        Aresta a1 = new Aresta(v1, v2, "A-B");
        Aresta a2 = new Aresta(v2, v3, "B-C");

        // Adicionando vértices e arestas ao Grafo
        grafo.inserirVertice(v1);
        grafo.inserirVertice(v2);
        grafo.inserirVertice(v3);
        grafo.inserirAresta(a1);
        grafo.inserirAresta(a2);

        // Adicionando valor à uma aresta existente
        a1.setValor("Nova-Aresta-Valor");

        // Testando métodos de inserção e direcionamento
        Aresta a3 = grafo.inserirArestaDirecionada(v3, v1, "C-A");
        System.out.println("Arestas no Grafo: " + grafo.arestas());

        // Testando métodos de verificação
        System.out.println("Contém vértice " + v1.getValor() + "? " + grafo.contemVertice(v1));
        System.out.println("Contém aresta " + a1.getValor() + "? " + grafo.contemAresta(a1));

        // Testando métodos de direcionamento
        System.out.println("Aresta " + a1.getValor() + " é direcionada? " + grafo.éDirecionada(a1));
        System.out.println("Aresta " + a3.getValor() + " é direcionada? " + grafo.éDirecionada(a3));

        // Testando método de arestas incidentes
        System.out.println("Arestas incidentes a " + v2.getValor() + ": " + grafo.arestasIncidentes(v2));

        // Testando métodos de remoção
        grafo.removerVertice(v2);
        System.out.println("Vertices no Grafo após remover " + v2.getValor() + ": " + grafo.vertices());
        System.out.println("Arestas no Grafo após remover " + v2.getValor() + ": " + grafo.arestas());

        // Testando métodos de substituição
        grafo.substituir(v1, "X");
        grafo.substituir(a1, "X-Y");
        System.out.println("Vertices no Grafo após substituir " + v1.getValor() + ": " + grafo.vertices());
        System.out.println("Arestas no Grafo após substituir " + a1.getValor() + ": " + grafo.arestas());

        // Testando outros métodos
        System.out.println("Vizinhos de " + v1.getValor() + ": " + grafo.obterVizinhos(v1));
        System.out.println("Grau do vértice " + v2.getValor() + ": " + grafo.grauDoVertice(v2));
        System.out.println("Vertices finais de " + a1.getValor() + ": " + grafo.finalVertices(a1));
        System.out.println("Oposto de " + v2.getValor() + " em relação a " + a1.getValor() + ": " + grafo.oposto(v2, a1));
        System.out.println(v1.getValor() + " é adjacente a " + v3.getValor() + "? " + grafo.éAdjacente(v1, v3));

        // Testando métodos de coleções
        System.out.println("Vertices no Grafo: " + grafo.vertices());
        System.out.println("Arestas no Grafo: " + grafo.arestas());
    }
}
