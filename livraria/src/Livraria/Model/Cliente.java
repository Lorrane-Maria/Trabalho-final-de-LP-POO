package Livraria.Model;

import Livraria.Util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Cliente extends Usuario {
    private Endereco endereco;
    private String telefone;
    private int cod;

    public Cliente(String tipo, String nome, String email, String senha, Endereco endereco, String telefone) {
        super(nome, email, senha, "cliente");
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public int getCod() { return cod; } // Método para obter o código do cliente
    public void setCod(int cod) { this.cod = cod; } // Método para definir o código do cliente

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", endereco=" + endereco +
                ", senha='" + getSenha() + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }

    public static void inserir(int usuarioId, int enderecoId, String telefone) throws SQLException {
        String insertClienteSQL = "INSERT INTO cliente (cod_usu, endereco_cod, telefone) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertClienteSQL)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, enderecoId);
            stmt.setString(3, telefone);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
