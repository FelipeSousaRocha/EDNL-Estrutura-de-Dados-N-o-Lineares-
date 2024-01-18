import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Caminho {
    private Stack<No> passos;

    public Caminho() {
        passos = new Stack<>();
    }

    // Método para adicionar um passo ao caminho
    public void adicionarPasso(No no) {
        passos.push(no);
    }

    // Método para obter o caminho como uma lista de nós
    public Stack<No> getPassos() {
        return passos;
    }

    // Método para imprimir o caminho
    public void imprimirCaminho() {
        List<No> caminhoList = new ArrayList<>(passos);
        Collections.reverse(caminhoList);

        for (No passo : caminhoList) {
            System.out.print("(" + passo.getLinha() + ", " + passo.getColuna() + ") ");
        }
        System.out.println();
    }

}
