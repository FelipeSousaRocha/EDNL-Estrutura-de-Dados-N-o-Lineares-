// Na classe Vertice foi criado um construtor que recebe um valor
// associado ao vértice e métodos para acessar e
// modificar esse valor.
// Agora, o vértice pode ser representado por um valor.

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private String valor;
    private List<Aresta> vizinhos;

    public Vertice(String valor) {
        this.valor = valor;
        this.vizinhos = new ArrayList<>();
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<Aresta> getVizinhos() {
        return vizinhos;
    }

    public void adicionarVizinho(Aresta aresta) {
        vizinhos.add(aresta);
    }

    @Override
    public String toString() {
        return valor;
    }
}


