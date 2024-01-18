package RN;

public class Main {
    public static void main(String[] args) {
        ArvoreRubroNegra arvore = new ArvoreRubroNegra();

        // Inserir elementos na árvore
        arvore.inserir(10);
        arvore.inserir(6);
        arvore.inserir(22);
        arvore.inserir(3);
        arvore.inserir(8);
        arvore.inserir(7);
        arvore.inserir(9);

        // Buscar um valor na árvore
        No resultadoBusca = arvore.buscar(8);
        if (resultadoBusca != null) {
            System.out.println("Valor 8 encontrado na árvore.");
        } else {
            System.out.println("Valor 8 não encontrado na árvore.");
        }

        // Mostrar a árvore
        arvore.mostrarArvore();
    }
}


