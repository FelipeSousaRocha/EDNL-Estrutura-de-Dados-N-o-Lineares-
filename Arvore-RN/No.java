package RN;

public class No {
    private int valor;
    private boolean ehRubro; // true se o nó é vermelho, false se é preto
    private No pai;
    private No esquerda;
    private No direita;

    public No(int valor, boolean ehRubro, No pai, No esquerda, No direita) {
        this.valor = valor;
        this.ehRubro = ehRubro;
        this.pai = pai;
        this.esquerda = esquerda;
        this.direita = direita;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean isEhRubro() {
        return ehRubro;
    }

    public void setEhRubro(boolean ehRubro) {
        this.ehRubro = ehRubro;
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }

    // Método para obter o irmão do nó
    public No getIrmao() {
        if (pai != null) {
            if (this == pai.getEsquerda()) {
                return pai.getDireita();
            } else {
                return pai.getEsquerda();
            }
        }
        return null;
    }

    // Método para obter o avô do nó
    public No getAvo() {
        if (pai != null && pai.getPai() != null) {
            return pai.getPai();
        }
        return null;
    }


    // Método para verificar o tio do nó
    public No getTio() {
        if (pai == null || pai.getPai() == null) {
            return null; // Não há tio se não houver pai ou avô
        }
        if (this.pai == this.pai.getPai().getEsquerda()) {
            return this.pai.getPai().getDireita();
        } else {
            return this.pai.getPai().getEsquerda();
        }
    }

}



