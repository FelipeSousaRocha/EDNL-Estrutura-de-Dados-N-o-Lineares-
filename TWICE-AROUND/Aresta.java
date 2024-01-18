public class Aresta {
    private Vertice origem;
    private Vertice destino;
    private String valor;
    private boolean direcionada;
    private boolean usada; // Adicionado campo para controlar se a aresta foi utilizada

    public Aresta(Vertice origem, Vertice destino, String valor) {
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.direcionada = false;
        this.usada = false; // Inicialmente, a aresta não foi utilizada
    }

    public Aresta(Vertice origem, Vertice destino, String valor, boolean direcionada) {
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.direcionada = direcionada;
        this.usada = false; // Inicialmente, a aresta não foi utilizada
    }

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

    public boolean isUsada() {
        return usada;
    }

    public void setUsada(boolean usada) {
        this.usada = usada;
    }

    // Método para reiniciar o estado da aresta
    public void reset() {
        this.usada = false;
    }

    @Override
    public String toString() {
        return "Aresta{" +
                "origem=" + origem.getValor() +
                ", destino=" + destino.getValor() +
                ", valor='" + valor + '\'' +
                ", direcionada=" + direcionada +
                ", usada=" + usada + // Adicionado à representação de string
                '}';
    }
}