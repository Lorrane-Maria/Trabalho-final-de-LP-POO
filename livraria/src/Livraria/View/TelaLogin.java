/*package Livraria.View;

import Livraria.Controller.ClienteController;
import Livraria.Model.Cliente;

import Livraria.View.GradientPanel;
import Livraria.View.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TelaLogin extends JFrame {
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JButton btnLogin;
    private JButton btnCadastrar;

    public TelaLogin() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com gradiente
        GradientPanel mainPanel = new GradientPanel(Color.decode("#b47688"), Color.decode("#a8a8a8"));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JTextField txtEmail = new JTextField(20);
        JPasswordField txtSenha = new JPasswordField(20);

        // Ajustando os botões
        RoundedButton btnLogin = new RoundedButton("Entrar");
        btnLogin.setBackground(Color.decode("#af9099"));
        btnLogin.setForeground(Color.decode("#ffffff"));
        btnLogin.setBorder(BorderFactory.createEmptyBorder());

        RoundedButton btnCadastrar = new RoundedButton("Cadastrar");
        btnCadastrar.setBackground(Color.decode("#a8a8a8"));
        btnCadastrar.setForeground(Color.BLACK);
        btnCadastrar.setBorder(BorderFactory.createEmptyBorder());

        mainPanel.add(new JLabel("Email:"));
        mainPanel.add(txtEmail);
        mainPanel.add(new JLabel("Senha:"));
        mainPanel.add(txtSenha);
        mainPanel.add(btnLogin);

        // Adicionando um painel para os botões de cadastro
        JPanel panelCadastrar = new JPanel();
        panelCadastrar.setOpaque(false); // Tornar o painel transparente
        panelCadastrar.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel lblMensagem = new JLabel("Ainda não tem conta?");
        lblMensagem.setForeground(Color.WHITE);
        panelCadastrar.add(lblMensagem);
        panelCadastrar.add(btnCadastrar);

        mainPanel.add(panelCadastrar);

        add(mainPanel);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClienteController clienteController = new ClienteController();
                try {
                    Cliente cliente = clienteController.login(txtEmail.getText(), new String(txtSenha.getPassword()));
                    if (cliente != null) {
                        JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                        dispose();
                        new TelaVitrine(cliente).setVisible(true);;
                    } else {
                        JOptionPane.showMessageDialog(null, "Email ou senha incorretos!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao realizar login: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaCadastro();
            }
        });

        setVisible(true);
    }
}*/

package Livraria.View;

import Livraria.Controller.ClienteController;
import Livraria.Model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TelaLogin extends JFrame {
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JButton btnLogin;
    private JButton btnCadastrar;

    public TelaLogin() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com gradiente
        GradientPanel mainPanel = new GradientPanel(Color.decode("#cd9fad"), Color.decode("#99cbd4"));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JLabel lblTitulo = new JLabel("Login");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.decode("#004D40"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(lblTitulo, gbc);


        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setForeground(Color.decode("#004D40"));
        mainPanel.add(lblEmail, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        txtEmail = new JTextField(20);
        mainPanel.add(txtEmail, gbc);

        // Campo de senha
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setForeground(Color.decode("#004D40"));
        mainPanel.add(lblSenha, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        txtSenha = new JPasswordField(20);
        mainPanel.add(txtSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnLogin = new RoundedButton("Entrar");
        btnLogin.setBackground(Color.decode("#a5c6c0"));
        btnLogin.setForeground(Color.decode("#004D40"));
        mainPanel.add(btnLogin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel panelCadastrar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCadastrar.setOpaque(false);

        JLabel lblMensagem = new JLabel("Ainda não tem conta?");
        lblMensagem.setForeground(Color.decode("#004D40"));
        panelCadastrar.add(lblMensagem);

        btnCadastrar = new RoundedButton("Cadastrar-se");
        btnCadastrar.setBackground(Color.decode("#a5c6c0"));
        btnCadastrar.setForeground(Color.decode("#004D40"));
        panelCadastrar.add(btnCadastrar);

        mainPanel.add(panelCadastrar, gbc);

        add(mainPanel);

        // Ação do botão de login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClienteController clienteController = new ClienteController();
                try {
                    Cliente cliente = clienteController.login(txtEmail.getText(), new String(txtSenha.getPassword()));
                    if (cliente != null) {
                        JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                        dispose();
                        new TelaVitrine(cliente).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Email ou senha incorretos!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao realizar login: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão de cadastro
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaCadastro().setVisible(true);
            }
        });

        setVisible(true);
    }
}

