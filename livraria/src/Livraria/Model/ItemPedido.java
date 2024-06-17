package Livraria.Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static Livraria.Util.DatabaseConnection.getConnection;

public class ItemPedido {
    private Produto produto;
    private int quantidade;
    private double preco;

    public ItemPedido(Produto produto, int quantidade, double preco) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public double calcularSubtotal() {
        return this.quantidade * this.preco;
    }

    public void inserir(Pedido pedido) throws SQLException {
        try(Connection conn = getConnection(); Statement stmt = conn.createStatement()){
            pedido.inserir(conn);
        } // Chama o método inserir de Pedido
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "produto=" + produto.getNome() +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                '}';
    }
}
