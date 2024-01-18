import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Labirinto {
    private int[][] matriz;
    private int linhaInicial;
    private int colunaInicial;
    private int linhaSaida;
    private int colunaSaida;

    public Labirinto(String arquivo) {
        lerLabirinto(arquivo);
    }

    private void lerLabirinto(String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            int numeroLinhas = 0;
            int numeroColunas = 0;

            while ((linha = br.readLine()) != null) {
                numeroLinhas++;
                numeroColunas = linha.length();
            }

            matriz = new int[numeroLinhas][numeroColunas];

            // Não é necessário fechar o BufferedReader dentro do try-with-resources

            // Criar uma nova instância de BufferedReader para reiniciar a leitura do arquivo
            try (BufferedReader br2 = new BufferedReader(new FileReader(arquivo))) {
                for (int i = 0; (linha = br2.readLine()) != null; i++) {
                    for (int j = 0; j < linha.length(); j++) {
                        char caractere = linha.charAt(j);
                        int valor = Character.getNumericValue(caractere);

                        matriz[i][j] = valor;

                        if (valor == 2) {
                            linhaInicial = i;
                            colunaInicial = j;
                        } else if (valor == 3) {
                            linhaSaida = i;
                            colunaSaida = j;
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Método para obter o custo entre dois nós
    public int getCusto(No noOrigem, No noDestino) {
        // Implemente a lógica para calcular o custo entre dois nós
        // Pode ser a distância entre eles, por exemplo
        return 1; // Altere conforme a lógica real do seu problema
    }

    // Método para obter os vizinhos de um nó
    public java.util.List<No> getVizinhos(No no) {
        List<No> vizinhos = new ArrayList<>();

        int linhaAtual = no.getLinha();
        int colunaAtual = no.getColuna();

        // Verificar vizinho acima
        if (ehPosicaoValida(linhaAtual - 1, colunaAtual)) {
            vizinhos.add(new No(linhaAtual - 1, colunaAtual));
        }

        // Verificar vizinho abaixo
        if (ehPosicaoValida(linhaAtual + 1, colunaAtual)) {
            vizinhos.add(new No(linhaAtual + 1, colunaAtual));
        }

        // Verificar vizinho à esquerda
        if (ehPosicaoValida(linhaAtual, colunaAtual - 1)) {
            vizinhos.add(new No(linhaAtual, colunaAtual - 1));
        }

        // Verificar vizinho à direita
        if (ehPosicaoValida(linhaAtual, colunaAtual + 1)) {
            vizinhos.add(new No(linhaAtual, colunaAtual + 1));
        }

        return vizinhos;
    }

    // Getters
    public int[][] getMatriz() {
        return matriz;
    }

    public int getLinhaInicial() {
        return linhaInicial;
    }

    public int getColunaInicial() {
        return colunaInicial;
    }

    public int getLinhaSaida() {
        return linhaSaida;
    }

    public int getColunaSaida() {
        return colunaSaida;
    }

    public int getNumeroLinhas() {
        return matriz.length;
    }

    public int getNumeroColunas() {
        return matriz[0].length;
    }

    // Método para imprimir o labirinto
    public void imprimirLabirinto() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Método para verificar se uma posição é válida
    public boolean ehPosicaoValida(int linha, int coluna) {
        return linha >= 0 && linha < getNumeroLinhas() &&
                coluna >= 0 && coluna < getNumeroColunas() &&
                matriz[linha][coluna] != 1;  // Verifica se não é uma parede
    }
}
