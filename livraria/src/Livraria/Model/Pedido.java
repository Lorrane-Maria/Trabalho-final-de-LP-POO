package Livraria.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private List<ItemPedido> itens;
    private String data;
    private double total;

    public Pedido(Cliente cliente, List<ItemPedido> itens, String data) {
        this.cliente = cliente;
        this.itens = itens;
        this.data = data;
        this.total = calcularTotal();
    }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<ItemPedido> getItens() { return itens; }
    public void setItens(List<ItemPedido> itens) { this.itens = itens; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    private double calcularTotal() {
        double total = 0.0;
        for (ItemPedido item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
        this.total = calcularTotal();
    }

    public void removerItem(ItemPedido item) {
        this.itens.remove(item);
        this.total = calcularTotal();
    }

    public void inserir(Connection conn) throws SQLException {
        // Inserir o pedido na tabela pedido
        String sqlInsertPedido = "INSERT INTO pedido (cliente_cod, data, total) VALUES (?, ?, ?)";
        try (PreparedStatement stmtPedido = conn.prepareStatement(sqlInsertPedido, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmtPedido.setInt(1, cliente.getCod()); // Supondo que Cliente tenha um atributo cod
            stmtPedido.setString(2, data);
            stmtPedido.setDouble(3, total);

            // Executar o insert
            stmtPedido.executeUpdate();

            // Obter o código gerado para o pedido
            int codPedido;
            try (var rsKeys = stmtPedido.getGeneratedKeys()) {
                if (rsKeys.next()) {
                    codPedido = rsKeys.getInt(1);

                    // Inserir os itens do pedido na tabela item_pedido
                    String sqlInsertItemPedido = "INSERT INTO item_pedido (pedido_cod, produto_cod, quantidade, preco) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement stmtItemPedido = conn.prepareStatement(sqlInsertItemPedido)) {
                        for (ItemPedido item : itens) {
                            stmtItemPedido.setInt(1, codPedido);
                            stmtItemPedido.setInt(2, item.getProduto().getCod());
                            stmtItemPedido.setInt(3, item.getQuantidade());
                            stmtItemPedido.setDouble(4, item.getPreco());
                            stmtItemPedido.executeUpdate();
                        }
                    }
                } else {
                    throw new SQLException("Falha ao obter o código gerado para o pedido.");
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "cliente=" + cliente.getNome() +
                ", itens=" + itens +
                ", data='" + data + '\'' +
                ", total=" + total +
                '}';
    }
}