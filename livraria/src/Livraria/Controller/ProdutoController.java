package Livraria.Controller;

import Livraria.Model.Produto;
import Livraria.Util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    public void adicionarProdutoEstoque(Produto produto, int quantidade) {
        produto.atualizarEstoque(quantidade);
    }

    public List<Produto> listarProdutosComEstoque() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE quantidade > 0";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int cod = rs.getInt("cod");
                String nome = rs.getString("nome");
                String autor = rs.getString("autor");
                String isbn = rs.getString("isbn");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");

                Produto produto = new Produto(cod, nome, autor, isbn, preco, quantidade);
                produtos.add(produto);
            }
        }

        return produtos;
    }
}
