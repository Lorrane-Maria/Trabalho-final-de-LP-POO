package Livraria.View;

import javax.swing.*;
import Livraria.Util.DatabaseConnection;

public class MainView {
    public static void main(String[] args) {
        // Cria as tabelas se não existirem
        DatabaseConnection db = new DatabaseConnection();
        db.createTablesIfNotExists();
        db.insertValuesInTables();

        // Iniciar e exibir a tela de login
        SwingUtilities.invokeLater(TelaLogin::new);
    }
}
