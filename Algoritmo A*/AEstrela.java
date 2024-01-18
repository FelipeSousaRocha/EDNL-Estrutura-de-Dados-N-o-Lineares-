import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.List;

public class AEstrela {
    public static void encontrarCaminho(Labirinto labirinto) {
        long startTime = System.nanoTime();

        PriorityQueue<No> filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(no -> no.getCustoTotal() + no.getHeuristica()));

        No noPartida = new No(labirinto.getLinhaInicial(), labirinto.getColunaInicial());
        noPartida.setCusto(0);
        noPartida.setHeuristica(calcularHeuristica(noPartida, labirinto));
        filaPrioridade.add(noPartida);

        boolean[][] visitados = new boolean[labirinto.getNumeroLinhas()][labirinto.getNumeroColunas()];

        while (!filaPrioridade.isEmpty()) {
            No noAtual = filaPrioridade.poll();

            if (noAtual.getLinha() == labirinto.getLinhaSaida() && noAtual.getColuna() == labirinto.getColunaSaida()) {
                reconstruirCaminho(noAtual, labirinto);
                long endTime = System.nanoTime();
                long totalTime = endTime - startTime;
                System.out.println("Tempo gasto pelo Algoritmo A*: " + totalTime + " nanossegundos");
                return;
            }

            visitados[noAtual.getLinha()][noAtual.getColuna()] = true;

            List<No> vizinhos = labirinto.getVizinhos(noAtual);
            for (No vizinho : vizinhos) {
                if (!visitados[vizinho.getLinha()][vizinho.getColuna()]) {
                    int novoCusto = noAtual.getCusto() + labirinto.getCusto(noAtual, vizinho);
                    int heuristica = calcularHeuristica(vizinho, labirinto);

                    if (!filaPrioridade.contains(vizinho) || novoCusto + heuristica < vizinho.getCustoTotal()) {
                        vizinho.setCusto(novoCusto);
                        vizinho.setHeuristica(heuristica);
                        vizinho.setPai(noAtual);
                        filaPrioridade.add(vizinho);
                    }
                }
            }
        }

        System.out.println("Não há caminho possível para a saída.");
    }

    private static int calcularHeuristica(No no, Labirinto labirinto) {
        int linhaDestino = labirinto.getLinhaSaida();
        int colunaDestino = labirinto.getColunaSaida();

        int linhaAtual = no.getLinha();
        int colunaAtual = no.getColuna();

        // Distância de Manhattan
        return Math.abs(linhaDestino - linhaAtual) + Math.abs(colunaDestino - colunaAtual);
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
