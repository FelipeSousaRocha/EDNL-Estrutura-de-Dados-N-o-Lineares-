package AVL;

public class ArvoreBinariaPesquisa {
    No raiz;

    public ArvoreBinariaPesquisa() {
        raiz = null;
    }

    // Classe No (mesma definição que você forneceu)

    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    private No inserirRecursivo(No no, int valor) {
        if (no == null) {
            return new No(valor);
        }

        if (valor < no.valor) {
            no.esquerda = inserirRecursivo(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = inserirRecursivo(no.direita, valor);
        } else {
            // Valor duplicado, não faz nada
            return no;
        }

        return no;
    }

    public void remover(int valor) {
        raiz = removerRecursivo(raiz, valor);
    }

    private No removerRecursivo(No raiz, int valor) {
        if (raiz == null) {
            return raiz;
        }

        if (valor < raiz.valor) {
            raiz.esquerda = removerRecursivo(raiz.esquerda, valor);
        } else if (valor > raiz.valor) {
            raiz.direita = removerRecursivo(raiz.direita, valor);
        } else {
            if (raiz.esquerda == null) {
                return raiz.direita;
            } else if (raiz.direita == null) {
                return raiz.esquerda;
            }

            raiz.valor = encontrarMinimo(raiz.direita).valor;
            raiz.direita = removerRecursivo(raiz.direita, raiz.valor);
        }

        return raiz;
    }

    private No encontrarMinimo(No no) {
        while (no.esquerda != null) {
            no = no.esquerda;
        }
        return no;
    }

    public boolean buscar(int valor) {
        return buscarRecursivamente(raiz, valor);
    }

    private boolean buscarRecursivamente(No no, int valor) {
        if (no == null) {
            return false;
        }

        if (valor == no.valor) {
            return true;
        }

        if (valor < no.valor) {
            return buscarRecursivamente(no.esquerda, valor);
        } else {
            return buscarRecursivamente(no.direita, valor);
        }
    }

    public void emOrdem() {
        emOrdemRecursivo(raiz);
        System.out.println();
    }

    private void emOrdemRecursivo(No no) {
        if (no != null) {
            emOrdemRecursivo(no.esquerda);
            System.out.print(no.valor + " ");
            emOrdemRecursivo(no.direita);
        }
    }
}