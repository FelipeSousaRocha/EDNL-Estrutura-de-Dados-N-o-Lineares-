package AVL;

public class Main {
    public static void main(String[] args) {
        ArvoreAVL arvoreAVL = new ArvoreAVL();

        arvoreAVL.inserir(10);
        arvoreAVL.inserir(5);
        arvoreAVL.inserir(15);
        arvoreAVL.inserir(2);
        arvoreAVL.inserir(8);
        arvoreAVL.inserir(22);

        System.out.println("Árvore AVL original:");
        arvoreAVL.imprimirArvore();

        boolean encontrado = arvoreAVL.buscar(5);
        System.out.println("Valor 5 encontrado na árvore AVL: " + encontrado);

        arvoreAVL.remover(5);
        System.out.println("Árvore AVL após remover o valor 5:");
        arvoreAVL.imprimirArvore();

        // Teste adicional
        arvoreAVL.inserir(12);
        arvoreAVL.inserir(18);
        System.out.println("Árvore AVL após inserção de 12 e 18:");
        arvoreAVL.imprimirArvore();

        arvoreAVL.remover(15);
        System.out.println("Árvore AVL após remover o valor 15:");
        arvoreAVL.imprimirArvore();
    }
}
