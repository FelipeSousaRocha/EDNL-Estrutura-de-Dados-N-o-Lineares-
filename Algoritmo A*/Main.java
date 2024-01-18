public class Main {
    public static void main(String[] args) {
        Labirinto labirinto = new Labirinto("D:\\ednl\\TadGrafo\\Algoritmo A\\labirinto.dat");

        System.out.println("Labirinto inicial:");
        labirinto.imprimirLabirinto();

        System.out.println("\nAlgoritmo A*:");
        AEstrela.encontrarCaminho(labirinto);

        System.out.println("\nAlgoritmo de Dijkstra:");
        Dijkstra.encontrarCaminho(labirinto);
    }
}
