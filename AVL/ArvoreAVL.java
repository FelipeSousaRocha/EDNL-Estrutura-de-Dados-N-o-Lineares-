package AVL;

public class ArvoreAVL extends ArvoreBinariaPesquisa {

    @Override
    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
        verificarEAtualizarBalanceamento(raiz);
    }

    private void verificarEAtualizarBalanceamento(No no) {
        if (no == null) {
            return;
        }

        // Verifique o balanceamento após a inserção
        if (!verificarBalanceamento(no)) {
            int fatorBalanceamento = calcularFatorBalanceamento(no);
            if (fatorBalanceamento >= 2) {
                if (calcularFatorBalanceamento(no.esquerda) >= 0) {
                    raiz = rotacaoDireita(no);
                } else {
                    raiz = rotacaoDuplaDireita(no);
                }
            } else if (fatorBalanceamento <= -2) {
                if (calcularFatorBalanceamento(no.direita) <= 0) {
                    raiz = rotacaoEsquerda(no);
                } else {
                    raiz = rotacaoDuplaEsquerda(no);
                }
            }
        }

        verificarEAtualizarBalanceamento(no.esquerda);
        verificarEAtualizarBalanceamento(no.direita);
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
            return no; // Valor duplicado, não faz nada
        }

        // Atualize a altura do nó atual
        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));

        int fatorBalanceamento = calcularFatorBalanceamento(no);

        if (fatorBalanceamento > 1) {
            if (valor < no.esquerda.valor) {
                return rotacaoDireita(no);
            } else {
                return rotacaoDuplaDireita(no);
            }
        }

        if (fatorBalanceamento < -1) {
            if (valor > no.direita.valor) {
                return rotacaoEsquerda(no);
            } else {
                return rotacaoDuplaEsquerda(no);
            }
        }

        return no;
    }

    @Override
    public void remover(int valor) {
        raiz = removerRecursivo(raiz, valor);
        verificarEAtualizarBalanceamento(raiz);
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

            // Encontre o nó mínimo na subárvore direita
            No temp = encontrarMinimo(raiz.direita);
            raiz.valor = temp.valor;
            raiz.direita = removerRecursivo(raiz.direita, temp.valor);
        }

        // Atualize a altura do nó atual
        raiz.altura = 1 + Math.max(altura(raiz.esquerda), altura(raiz.direita));

        int fatorBalanceamento = calcularFatorBalanceamento(raiz);

        if (fatorBalanceamento > 1) {
            if (calcularFatorBalanceamento(raiz.esquerda) >= 0) {
                return rotacaoDireita(raiz);
            } else {
                return rotacaoDuplaDireita(raiz);
            }
        }

        if (fatorBalanceamento < -1) {
            if (calcularFatorBalanceamento(raiz.direita) <= 0) {
                return rotacaoEsquerda(raiz);
            } else {
                return rotacaoDuplaEsquerda(raiz);
            }
        }

        return raiz;
    }

    public int calcularAltura(No no) {
        if (no == null) {
            return 0;
        }
        int alturaEsquerda = calcularAltura(no.esquerda);
        int alturaDireita = calcularAltura(no.direita);
        return Math.max(alturaEsquerda, alturaDireita) + 1;
    }

    public boolean verificarBalanceamento(No no) {
        if (no == null) {
            return true;
        }
        int fatorBalanceamento = calcularFatorBalanceamento(no);
        if (Math.abs(fatorBalanceamento) > 1) {
            return false;
        }
        return verificarBalanceamento(no.esquerda) && verificarBalanceamento(no.direita);
    }

    private No encontrarMinimo(No no) {
        while (no.esquerda != null) {
            no = no.esquerda;
        }
        return no;
    }

    @Override
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

    private int calcularFatorBalanceamento(No no) {
        if (no == null) {
            return 0;
        }
        return altura(no.esquerda) - altura(no.direita);
    }

    private int altura(No no) {
        if (no == null) {
            return 0;
        }
        return no.altura;
    }

    private No rotacaoEsquerda(No y) {
        No x = y.direita;
        No T2 = x.esquerda;

        x.esquerda = y;
        y.direita = T2;

        // Atualize as alturas após a rotação
        y.altura = 1 + Math.max(altura(y.esquerda), altura(y.direita));
        x.altura = 1 + Math.max(altura(x.esquerda), altura(x.direita));

        return x;
    }

    private No rotacaoDireita(No x) {
        No y = x.esquerda;
        No T2 = y.direita;

        y.direita = x;
        x.esquerda = T2;

        // Atualize as alturas após a rotação
        x.altura = 1 + Math.max(altura(x.esquerda), altura(x.direita));
        y.altura = 1 + Math.max(altura(y.esquerda), altura(y.direita));

        return y;
    }

    private No rotacaoDuplaEsquerda(No y) {
        y.direita = rotacaoDireita(y.direita);
        return rotacaoEsquerda(y);
    }

    private No rotacaoDuplaDireita(No x) {
        x.esquerda = rotacaoEsquerda(x.esquerda);
        return rotacaoDireita(x);
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

    public void imprimirArvore(No no, String espaco, boolean direita) {
        if (no != null) {
            System.out.print(espaco);
            if (direita) {
                System.out.print("└── ");
                espaco += "    ";
            } else {
                System.out.print("├── ");
                espaco += "│   ";
            }
            System.out.println(no.valor + " (FB: " + calcularFatorBalanceamento(no) + ")");
            imprimirArvore(no.esquerda, espaco, false);
            imprimirArvore(no.direita, espaco, true);
        }
    }


    public void imprimirArvore() {
        imprimirArvore(raiz, "", false);
    }
}
