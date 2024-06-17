package Livraria.Model;

public class Estoque {
    private Produto produto;
    private int quantidade;

    public Estoque(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public boolean verificarDisponibilidade(int quantidade) {
        return this.quantidade >= quantidade;
    }
}
