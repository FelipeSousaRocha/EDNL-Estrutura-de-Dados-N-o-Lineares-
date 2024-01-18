public class Aresta {
    private Vertice origem; // De onde vem
    private Vertice destino; // Para onde vai
    private String valor; // Valor associado à aresta
    private boolean direcionada; // Indica se a aresta é direcionada

    // Construtor para arestas não direcionadas
    public Aresta(Vertice origem, Vertice destino, String valor) {
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.direcionada = false; // Por padrão, consideramos a aresta como não direcionada
    }

    // Construtor para arestas direcionadas
    public Aresta(Vertice origem, Vertice destino, String valor, boolean direcionada) {
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.direcionada = direcionada;
    }

    // Métodos Get e Set
    public Vertice getOrigem() {
        return origem;
    }

    public Vertice getDestino() {
        return destino;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Vertice getOposto(Vertice v) {
        if (v.equals(origem)) {
            return destino;
        } else if (v.equals(destino)) {
            return origem;
        } else {
            throw new IllegalArgumentException("O vértice não é incidente à aresta");
        }
    }


    public boolean eDirecionada() {
        return direcionada;
    }

    public void setDirecionada(boolean direcionada) {
        this.direcionada = direcionada;
    }

    // Sobrescrita do método toString() para uma representação mais amigável
    @Override
    public String toString() {
        return "Aresta{" +
                "origem=" + origem.getValor() +
                ", destino=" + destino.getValor() +
                ", valor='" + valor + '\'' +
                ", direcionada=" + direcionada +
                '}';
    }
}
