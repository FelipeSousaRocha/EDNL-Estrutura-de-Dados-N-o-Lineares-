package RN;

public class ArvoreRubroNegra {
    private No raiz;
    private No nulo;

    public ArvoreRubroNegra() {
        nulo = new No(0, false, null, null, null);
        raiz = nulo;
    }

    // Método para inserir um valor na árvore
    public void inserir(int valor) {
        No novoNo = new No(valor, true, nulo, nulo, nulo);
        raiz = inserirRecursivo(raiz, novoNo); // Atualizar a raiz
        corrigirInsercao(novoNo);
    }

    // Método recursivo para inserção
    private No inserirRecursivo(No raizSubarvore, No novoNo) {
        if (raizSubarvore == nulo) {
            return novoNo;
        } else if (novoNo.getValor() < raizSubarvore.getValor()) {
            raizSubarvore.setEsquerda(inserirRecursivo(raizSubarvore.getEsquerda(), novoNo));
            raizSubarvore.getEsquerda().setPai(raizSubarvore);
        } else {
            raizSubarvore.setDireita(inserirRecursivo(raizSubarvore.getDireita(), novoNo));
            raizSubarvore.getDireita().setPai(raizSubarvore);
        }
        return raizSubarvore;
    }

    // Método para realizar uma rotação à esquerda
    private void rotacaoEsquerda(No no) {
        No direitaFilho = no.getDireita();
        no.setDireita(direitaFilho.getEsquerda());

        if (direitaFilho.getEsquerda() != nulo) {
            direitaFilho.getEsquerda().setPai(no);
        }

        direitaFilho.setPai(no.getPai());

        if (no.getPai() == nulo) {
            raiz = direitaFilho;
        } else if (no == no.getPai().getEsquerda()) {
            no.getPai().setEsquerda(direitaFilho);
        } else {
            no.getPai().setDireita(direitaFilho);
        }

        direitaFilho.setEsquerda(no);
        no.setPai(direitaFilho);
    }

    // Método para realizar uma rotação à direita
    private void rotacaoDireita(No no) {
        No esquerdaFilho = no.getEsquerda();
        no.setEsquerda(esquerdaFilho.getDireita());

        if (esquerdaFilho.getDireita() != nulo) {
            esquerdaFilho.getDireita().setPai(no);
        }

        esquerdaFilho.setPai(no.getPai());

        if (no.getPai() == nulo) {
            raiz = esquerdaFilho;
        } else if (no == no.getPai().getDireita()) {
            no.getPai().setDireita(esquerdaFilho);
        } else {
            no.getPai().setEsquerda(esquerdaFilho);
        }

        esquerdaFilho.setDireita(no);
        no.setPai(esquerdaFilho);
    }

    // Método para realizar uma rotação à esquerda dupla
    private void rotacaoEsquerdaDupla(No no) {
        rotacaoDireita(no.getDireita());
        rotacaoEsquerda(no);
    }

    // Método para realizar uma rotação à direita dupla
    private void rotacaoDireitaDupla(No no) {
        rotacaoEsquerda(no.getEsquerda());
        rotacaoDireita(no);
    }

    // Método para corrigir a árvore após a inserção
    private void corrigirInsercao(No noInserido) {
        while (noInserido.getPai().isEhRubro()) {
            if (noInserido.getPai() == noInserido.getPai().getPai().getEsquerda()) {
                No tio = noInserido.getPai().getPai().getDireita();
                if (tio.isEhRubro()) {
                    // Caso 2: Tio é vermelho
                    noInserido.getPai().setEhRubro(false);
                    tio.setEhRubro(false);
                    noInserido.getPai().getPai().setEhRubro(true);
                    noInserido = noInserido.getPai().getPai();
                } else {
                    if (noInserido == noInserido.getPai().getDireita()) {
                        // Caso 3a: Rotação direita simples
                        noInserido = noInserido.getPai();
                        rotacaoEsquerda(noInserido);
                    }
                    // Caso 3b: Rotação esquerda simples
                    noInserido.getPai().setEhRubro(false);
                    noInserido.getPai().getPai().setEhRubro(true);
                    rotacaoDireita(noInserido.getPai().getPai());
                }
            } else {
                No tio = noInserido.getPai().getPai().getEsquerda();
                if (tio.isEhRubro()) {
                    // Caso 2: Tio é vermelho
                    noInserido.getPai().setEhRubro(false);
                    tio.setEhRubro(false);
                    noInserido.getPai().getPai().setEhRubro(true);
                    noInserido = noInserido.getPai().getPai();
                } else {
                    if (noInserido == noInserido.getPai().getEsquerda()) {
                        // Caso 3c: Rotação esquerda dupla
                        noInserido = noInserido.getPai();
                        rotacaoDireita(noInserido);
                    }
                    // Caso 3d: Rotação direita dupla
                    noInserido.getPai().setEhRubro(false);
                    noInserido.getPai().getPai().setEhRubro(true);
                    rotacaoEsquerda(noInserido.getPai().getPai());
                }
            }
        }
        raiz.setEhRubro(false);
    }

    // Método para buscar um valor na árvore
    public No buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    // Método recursivo para busca
    private No buscarRecursivo(No no, int valor) {
        if (no == nulo || valor == no.getValor()) {
            return no;
        }

        if (valor < no.getValor()) {
            return buscarRecursivo(no.getEsquerda(), valor);
        } else {
            return buscarRecursivo(no.getDireita(), valor);
        }
    }

    // Método para mostrar a árvore de forma hierárquica com informações sobre rubro ou negro
    public void mostrarArvore() {
        mostrarArvoreRecursivo(raiz, "", false);
    }

    // Método recursivo para exibição hierárquica com informações sobre rubro ou negro
    private void mostrarArvoreRecursivo(No no, String espaco, boolean direita) {
        if (no != nulo) {
            System.out.print(espaco);
            if (direita) {
                System.out.print("└── ");
                espaco += "    ";
            } else {
                System.out.print("├── ");
                espaco += "│   ";
            }
            System.out.println(no.getValor() + (no.isEhRubro() ? " (Rubro)" : " (Negro)"));

            // Chamadas recursivas com o novo valor de espaco
            mostrarArvoreRecursivo(no.getEsquerda(), espaco, false);
            mostrarArvoreRecursivo(no.getDireita(), espaco, true);
        }
    }
}
