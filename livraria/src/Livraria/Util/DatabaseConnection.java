package Livraria.Util;

import java.sql.*;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:livraria.db";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Driver JDBC do SQLite não encontrado");
        }
        return DriverManager.getConnection(URL);
    }

    public static void createTablesIfNotExists() {
        String createUsuarioTableSQL = "CREATE TABLE IF NOT EXISTS usuario (" +
                "cod INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "email TEXT UNIQUE NOT NULL, " +
                "senha TEXT NOT NULL, " +
                "tipo TEXT NOT NULL CHECK(tipo IN ('cliente', 'vendedor'))) ";

        String createClienteTableSQL = "CREATE TABLE IF NOT EXISTS cliente (" +
                "cod INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cod_usu INTEGER, " +
                "endereco_cod INTEGER, " +
                "telefone TEXT, " +
                "FOREIGN KEY(cod_usu) REFERENCES usuario(cod), " +
                "FOREIGN KEY(endereco_cod) REFERENCES endereco(cod))";

        String createVendedorTableSQL = "CREATE TABLE IF NOT EXISTS vendedor (" +
                "cod INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cod_usu INTEGER NOT NULL, " +
                "endereco_cod INTEGER NOT NULL, " +
                "telefone TEXT, " +
                "FOREIGN KEY(cod_usu) REFERENCES usuario(cod)," +
                "FOREIGN KEY(endereco_cod) REFERENCES endereco(cod))";

        String createEnderecoTableSQL = "CREATE TABLE IF NOT EXISTS endereco (" +
                "cod INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "rua TEXT NOT NULL, " +
                "numero TEXT NOT NULL, " +
                "bairro TEXT NOT NULL, " +
                "cidade TEXT NOT NULL, " +
                "estado TEXT NOT NULL, " +
                "cep TEXT NOT NULL)";

        String createProdutoTableSQL = "CREATE TABLE IF NOT EXISTS produto (" +
                "cod INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "autor TEXT NOT NULL, " +
                "isbn TEXT NOT NULL, " +
                "preco REAL NOT NULL, " +
                "quantidade INTEGER NOT NULL) ";

        String createPedidoTableSQL = "CREATE TABLE IF NOT EXISTS pedido (" +
                "cod INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cliente_cod INTEGER, " +
                "data TEXT NOT NULL, " +
                "FOREIGN KEY(cliente_cod) REFERENCES cliente(cod))";

        String createItemPedidoTableSQL = "CREATE TABLE IF NOT EXISTS item_pedido (" +
                "cod INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "pedido_cod INTEGER, " +
                "produto_cod INTEGER, " +
                "quantidade INTEGER NOT NULL, " +
                "FOREIGN KEY(pedido_cod) REFERENCES pedido(cod), " +
                "FOREIGN KEY(produto_cod) REFERENCES produto(cod))";

        String createFormaEnvioTableSQL = "CREATE TABLE IF NOT EXISTS forma_envio (" +
                "cod INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tipo_envio TEXT NOT NULL)";

        String createEstoqueTableSQL = "CREATE TABLE IF NOT EXISTS estoque (" +
                "cod INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "produto_cod INTEGER, " +
                "quantidade INTEGER NOT NULL, " +
                "FOREIGN KEY(produto_cod) REFERENCES produto(cod))";

        String createFornecedorTableSQL = "CREATE TABLE IF NOT EXISTS fornecedor (" +
                "cod INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "endereco_cod INTEGER, " +
                "telefone TEXT, " +
                "FOREIGN KEY(endereco_cod) REFERENCES endereco(cod))";

        String createLojaTableSQL = "CREATE TABLE IF NOT EXISTS loja (" +
                "cod INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "endereco_cod INTEGER, " +
                "telefone TEXT, " +
                "FOREIGN KEY(endereco_cod) REFERENCES endereco(cod))";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createUsuarioTableSQL);
            stmt.execute(createClienteTableSQL);
            stmt.execute(createVendedorTableSQL);
            stmt.execute(createEnderecoTableSQL);
            stmt.execute(createProdutoTableSQL);
            stmt.execute(createPedidoTableSQL);
            stmt.execute(createItemPedidoTableSQL);
            stmt.execute(createFormaEnvioTableSQL);
            stmt.execute(createEstoqueTableSQL);
            stmt.execute(createFornecedorTableSQL);
            stmt.execute(createLojaTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertValuesInTables() {

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            // Inserção de Endereços
            stmt.execute("INSERT OR IGNORE INTO endereco (rua, numero, bairro, cidade, estado, cep) VALUES " +
                    "('Rua Aurora Maria da Conceição', '1', 'Santa Cruz', 'Betim', 'Minas Gerais', '32667362')," +
                    "('Avenida Professora Maria Valéria Lemos Borsari', '12', 'Vila São José', 'Passos', 'Minas Gerais', '37903534')," +
                    "('Rua Alagoas', '23', 'Vila Rica', 'Sabará', 'Minas Gerais', '34580220')");

            // Inserção de Usuários e Vendedores
            stmt.execute("INSERT OR IGNORE INTO usuario (nome, email, senha, tipo) VALUES " +
                    "('Anne Shirley','anneshirley@gmail.com', 'avondela', 'vendedor')");

            // Obter o ID do usuário inserido ou existente
            int codUsuario = getCodUsuario(stmt, "anneshirley@gmail.com");

            // Inserção de vendedor apenas com as colunas que possui
            stmt.execute("INSERT OR IGNORE INTO vendedor (cod_usu, endereco_cod) VALUES " +
                    "(" + codUsuario + ", " +
                    "(SELECT cod FROM endereco WHERE rua='Rua Aurora Maria da Conceição' AND numero='1' AND bairro='Santa Cruz' AND cidade='Betim' AND estado='Minas Gerais' AND cep='32667362'))");

            // Inserção de Produtos (com verificação de existência pelo ISBN)
            insertOrUpdateProduto(conn, stmt, "978-85-01-11251-4", "É assim que acaba", "Colleen Hoover", 45.80, 10);
            insertOrUpdateProduto(conn, stmt, "978-85-61635-06-0", "Anne de Green Glables", "L. M. Montgomery", 50.00, 10);

            // Inserção de Fornecedores (com verificação de existência pelo nome)
            insertOrUpdateFornecedor(conn, stmt, "Marilia LTDA", "Avenida Professora Maria Valéria Lemos Borsari", "12", "Vila São José", "Passos", "Minas Gerais", "37903534", "3439824348");

            // Inserção de Lojas (com verificação de existência pelo nome)
            insertOrUpdateLoja(conn, stmt, "Palavras de Avonlea", "Rua Alagoas", "23", "Vila Rica", "Sabará", "Minas Gerais", "34580220", "36366674");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getCodUsuario(Statement stmt, String email) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT cod FROM usuario WHERE email='" + email + "'");
        if (rs.next()) {
            return rs.getInt("cod");
        }
        return -1; // Retorna -1 se não encontrar o usuário
    }

    private static void insertOrUpdateProduto(Connection conn, Statement stmt, String isbn, String nome, String autor, double preco, int quantidade) throws SQLException {
        // Verifica se o produto já existe pelo ISBN
        ResultSet rs = stmt.executeQuery("SELECT cod FROM produto WHERE isbn='" + isbn + "'");
        if (rs.next()) {
            // ISBN já existe, então atualiza a quantidade e o estoque
            int produtoCod = rs.getInt("cod");
            String updateQuantidadeSQL = "UPDATE produto SET quantidade = quantidade + ? WHERE cod = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateQuantidadeSQL)) {
                updateStmt.setInt(1, quantidade);
                updateStmt.setInt(2, produtoCod);
                updateStmt.executeUpdate();
            }
            // Atualiza também a tabela de estoque
            updateEstoque(conn, stmt, produtoCod, quantidade);
        } else {
            // ISBN não existe, então insere o produto e o estoque
            stmt.execute("INSERT INTO produto (nome, autor, isbn, preco, quantidade) VALUES " +
                    "('" + nome + "', '" + autor + "', '" + isbn + "', " + preco + ", " + quantidade + ")");
            int produtoCod = getLastInsertId(stmt);
            stmt.execute("INSERT INTO estoque (produto_cod, quantidade) VALUES (" + produtoCod + ", " + quantidade + ")");
        }
    }

    private static void updateEstoque(Connection conn, Statement stmt, int produtoCod, int quantidade) throws SQLException {
        // Atualiza a quantidade na tabela de estoque
        String updateEstoqueSQL = "UPDATE estoque SET quantidade = quantidade + ? WHERE produto_cod = ?";
        try (PreparedStatement updateStmt = conn.prepareStatement(updateEstoqueSQL)) {
            updateStmt.setInt(1, quantidade);
            updateStmt.setInt(2, produtoCod);
            updateStmt.executeUpdate();
        }
    }

    private static void insertOrUpdateFornecedor(Connection conn, Statement stmt, String nome, String rua, String numero, String bairro, String cidade, String estado, String cep, String telefone) throws SQLException {
        // Verifica se o fornecedor já existe pelo nome
        ResultSet rs = stmt.executeQuery("SELECT cod FROM fornecedor WHERE nome='" + nome + "'");
        if (!rs.next()) {
            // Fornecedor não existe, então insere
            stmt.execute("INSERT INTO fornecedor (nome, endereco_cod, telefone) VALUES " +
                    "('" + nome + "', (SELECT cod FROM endereco WHERE rua='" + rua + "' AND numero='" + numero + "' AND bairro='" + bairro + "' AND cidade='" + cidade + "' AND estado='" + estado + "' AND cep='" + cep + "'), '" + telefone + "')");
        }
    }

    private static void insertOrUpdateLoja(Connection conn, Statement stmt, String nome, String rua, String numero, String bairro, String cidade, String estado, String cep, String telefone) throws SQLException {
        // Verifica se a loja já existe pelo nome
        ResultSet rs = stmt.executeQuery("SELECT cod FROM loja WHERE nome='" + nome + "'");
        if (!rs.next()) {
            // Loja não existe, então insere
            stmt.execute("INSERT INTO loja (nome, endereco_cod, telefone) VALUES " +
                    "('" + nome + "', (SELECT cod FROM endereco WHERE rua='" + rua + "' AND numero='" + numero + "' AND bairro='" + bairro + "' AND cidade='" + cidade + "' AND estado='" + estado + "' AND cep='" + cep + "'), '" + telefone + "')");
        }
    }

    private static int getLastInsertId(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()");
        rs.next();
        return rs.getInt(1);
    }

    public void deleteDados() throws SQLException {
        String selectSql = "SELECT v.cod_usu, v.endereco_cod, u.cod, e.cod " +
                "FROM vendedor v " +
                "JOIN usuario u ON v.cod_usu = u.cod " +
                "JOIN endereco e ON v.endereco_cod = e.cod";

        String deleteVendedorSql = "DELETE FROM vendedor WHERE cod_usu = ? AND endereco_cod = ?";
        String deleteUsuarioSql = "DELETE FROM usuario WHERE cod = ?";
        String deleteEnderecoSql = "DELETE FROM endereco WHERE cod = ?";

        try (Connection conn = getConnection();
             PreparedStatement selectStatement = conn.prepareStatement(selectSql);
             PreparedStatement deleteVendedorStatement = conn.prepareStatement(deleteVendedorSql);
             PreparedStatement deleteUsuarioStatement = conn.prepareStatement(deleteUsuarioSql);
             PreparedStatement deleteEnderecoStatement = conn.prepareStatement(deleteEnderecoSql)) {

            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                int codigoUsuario = resultSet.getInt("cod_usu");
                int codUsuario = resultSet.getInt("cod");
                int codEnderecoUsuario = resultSet.getInt("cod");

                deleteVendedorStatement.setInt(1, codigoUsuario);
                deleteVendedorStatement.setInt(2, codEnderecoUsuario);
                deleteVendedorStatement.executeUpdate();

                deleteUsuarioStatement.setInt(1, codUsuario);
                deleteUsuarioStatement.executeUpdate();

                deleteEnderecoStatement.setInt(1, codEnderecoUsuario);
                deleteEnderecoStatement.executeUpdate();
            }
            System.out.println("Registros deletados com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao tentar deletar registros: " + e.getMessage());
        }
    }
}

