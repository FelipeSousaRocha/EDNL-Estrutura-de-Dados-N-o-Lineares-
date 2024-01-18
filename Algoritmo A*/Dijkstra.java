import java.util.PriorityQueue;
import java.util.Comparator;

public class Dijkstra {
    public static void encontrarCaminho(Labirinto labirinto) {
        long startTime = System.nanoTime();

        PriorityQueue<No> filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(No::getCusto));

        No noPartida = new No(labirinto.getLinhaInicial(), labirinto.getColunaInicial());
        noPartida.setCusto(0);
        filaPrioridade.add(noPartida);

        boolean[][] visitados = new boolean[labirinto.getNumeroLinhas()][labirinto.getNumeroColunas()];

        while (!filaPrioridade.isEmpty()) {
            No noAtual = filaPrioridade.poll();

            if (noAtual.getLinha() == labirinto.getLinhaSaida() && noAtual.getColuna() == labirinto.getColunaSaida()) {
                reconstruirCaminho(noAtual, labirinto);
                long endTime = System.nanoTime();
                long totalTime = endTime - startTime;
                System.out.println("Tempo gasto pelo Algoritmo de Dijkstra: " + totalTime + " nanossegundos");
                return;
            }

            visitados[noAtual.getLinha()][noAtual.getColuna()] = true;

            for (No vizinho : labirinto.getVizinhos(noAtual)) {
                if (!visitados[vizinho.getLinha()][vizinho.getColuna()]) {
                    int novoCusto = noAtual.getCusto() + labirinto.getCusto(noAtual, vizinho);

                    if (!filaPrioridade.contains(vizinho) || novoCusto < vizinho.getCusto()) {
                        vizinho.setCusto(novoCusto);
                        vizinho.setPai(noAtual);
                        filaPrioridade.add(vizinho);
                    }
                }
            }
        }

        System.out.println("Não há caminho possível para a saída.");
    }

    private static void reconstruirCaminho(No noFinal, Labirinto labirinto) {
        Caminho caminho = new Caminho();
        No noAtual = noFinal;

        while (noAtual != null) {
            caminho.adicionarPasso(noAtual);
            noAtual = noAtual.getPai();
        }

        System.out.println("Caminho encontrado:");
        caminho.imprimirCaminho();
    }
}
