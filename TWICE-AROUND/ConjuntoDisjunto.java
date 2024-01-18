import java.util.HashMap;
import java.util.Map;

public class ConjuntoDisjunto {

    private Map<Vertice, Vertice> pai;

    public ConjuntoDisjunto() {
        this.pai = new HashMap<>();
    }

    public void makeSet(Vertice vertice) {
        pai.put(vertice, vertice);
    }

    public Vertice encontrar(Vertice vertice) {
        if (!pai.get(vertice).equals(vertice)) {
            pai.put(vertice, encontrar(pai.get(vertice))); // Compressão de caminho
        }
        return pai.get(vertice);
    }

    public void union(Vertice vertice1, Vertice vertice2) {
        Vertice raiz1 = encontrar(vertice1);
        Vertice raiz2 = encontrar(vertice2);
        pai.put(raiz1, raiz2);
    }
}