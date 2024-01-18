public class No {
    private int linha;
    private int coluna;
    private int custo;
    private int heuristica;
    private No pai;

    public No(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    // Getters e Setters
    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public int getHeuristica() {
        return heuristica;
    }

    public void setHeuristica(int heuristica) {
        this.heuristica = heuristica;
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    // Método para calcular o custo total (custo + heurística)
    public int getCustoTotal() {
        return custo + heuristica;
    }
}
