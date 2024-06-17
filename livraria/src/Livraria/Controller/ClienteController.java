package Livraria.Controller;

import Livraria.Model.Cliente;
import Livraria.Model.Usuario;
import Livraria.Model.Endereco;
import Livraria.Util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteController {

    // Cadastra cliente
    public static void cadastrarCliente(String nome, String email, String senha, String tipo,
                                        Endereco endereco, String telefone) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            int usuarioId = Usuario.inserir(nome, email, senha, tipo);
            int enderecoId = Endereco.inserir(endereco.getRua(), endereco.getNumero(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado(), endereco.getCep());
            Cliente.inserir(usuarioId, enderecoId, telefone);

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Cliente login(String email, String senha) throws SQLException {
        String loginSQL = "SELECT u.*, c.telefone, e.* FROM usuario u " +
                "LEFT JOIN cliente c ON u.cod = c.cod " +
                "LEFT JOIN endereco e ON c.endereco_cod = e.cod " +
                "WHERE u.email = ? AND u.senha = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement loginStmt = conn.prepareStatement(loginSQL)) {
            loginStmt.setString(1, email);
            loginStmt.setString(2, senha);
            try (ResultSet rs = loginStmt.executeQuery()) {
                if (rs.next()) {
                    Endereco endereco = new Endereco(
                            rs.getString("rua"),
                            rs.getString("numero"),
                            rs.getString("bairro"),
                            rs.getString("cidade"),
                            rs.getString("estado"),
                            rs.getString("cep")
                    );

                    return new Cliente(
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("tipo"),
                            endereco,
                            rs.getString("telefone")
                    );
                } else {
                    return null; // Credenciais inválidas
                }
            }
        }
    }
}
