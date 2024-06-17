package Livraria.View;

import Livraria.Controller.ClienteController;
import Livraria.Model.Endereco;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TelaCadastro extends JFrame {

    private JTextField txtNome;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JTextField txtRua;
    private JTextField txtNumero;
    private JTextField txtBairro;
    private JTextField txtCidade;
    private JTextField txtEstado;
    private JTextField txtCep;
    private JTextField txtTelefone;
    private JButton btnCadastrar;

    public TelaCadastro() {
        setTitle("Cadastro");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com gradiente
        GradientPanel mainPanel = new GradientPanel(Color.decode("#b47688"), Color.decode("#a8a8a8"));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adiciona espaçamento interno

        Color labelColor = Color.decode("#004D40");

        // Painel do título
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        JLabel lblTitle = new JLabel("Cadastro");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setForeground(labelColor);
        titlePanel.add(lblTitle);
        mainPanel.add(titlePanel);

        // Painel de campos
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setOpaque(false);
        fieldsPanel.setLayout(new GridLayout(11, 2, 10, 10));

        txtNome = new JTextField(15);
        txtEmail = new JTextField(15);
        txtSenha = new JPasswordField(15);
        txtRua = new JTextField(15);
        txtNumero = new JTextField(15);
        txtBairro = new JTextField(15);
        txtCidade = new JTextField(15);
        txtEstado = new JTextField(15);
        txtCep = new JTextField(15);
        txtTelefone = new JTextField(15);

        // Aplicando o filtro numérico nos campos CEP e Telefone
        ((AbstractDocument) txtCep.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        ((AbstractDocument) txtTelefone.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setForeground(labelColor);
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(labelColor);
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setForeground(labelColor);
        JLabel lblRua = new JLabel("Rua:");
        lblRua.setForeground(labelColor);
        JLabel lblNumero = new JLabel("Número:");
        lblNumero.setForeground(labelColor);
        JLabel lblBairro = new JLabel("Bairro:");
        lblBairro.setForeground(labelColor);
        JLabel lblCidade = new JLabel("Cidade:");
        lblCidade.setForeground(labelColor);
        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setForeground(labelColor);
        JLabel lblCep = new JLabel("CEP:");
        lblCep.setForeground(labelColor);
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setForeground(labelColor);

        fieldsPanel.add(lblNome);
        fieldsPanel.add(txtNome);
        fieldsPanel.add(lblEmail);
        fieldsPanel.add(txtEmail);
        fieldsPanel.add(lblSenha);
        fieldsPanel.add(txtSenha);
        fieldsPanel.add(lblRua);
        fieldsPanel.add(txtRua);
        fieldsPanel.add(lblNumero);
        fieldsPanel.add(txtNumero);
        fieldsPanel.add(lblBairro);
        fieldsPanel.add(txtBairro);
        fieldsPanel.add(lblCidade);
        fieldsPanel.add(txtCidade);
        fieldsPanel.add(lblEstado);
        fieldsPanel.add(txtEstado);
        fieldsPanel.add(lblCep);
        fieldsPanel.add(txtCep);
        fieldsPanel.add(lblTelefone);
        fieldsPanel.add(txtTelefone);

        mainPanel.add(fieldsPanel);

        // Painel de botões
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnCadastrar = new RoundedButton("Cadastrar");
        btnCadastrar.setBackground(Color.decode("#af99a1"));
        btnCadastrar.setForeground(Color.decode("#ffffff"));
        btnCadastrar.setBorder(BorderFactory.createEmptyBorder());
        btnCadastrar.setPreferredSize(new Dimension(150, 40)); // Aumenta o tamanho do botão

        buttonsPanel.add(btnCadastrar);
        mainPanel.add(buttonsPanel);

        add(mainPanel);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClienteController clienteController = new ClienteController();
                String nome = txtNome.getText();
                String email = txtEmail.getText();
                String senha = new String(txtSenha.getPassword());
                String rua = txtRua.getText();
                String numero = txtNumero.getText();
                String bairro = txtBairro.getText();
                String cidade = txtCidade.getText();
                String estado = txtEstado.getText();
                String cep = txtCep.getText();
                String telefone = txtTelefone.getText();

                // Validação dos campos
                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || rua.isEmpty() || numero.isEmpty() ||
                        bairro.isEmpty() || cidade.isEmpty() || estado.isEmpty() || cep.isEmpty() || telefone.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado, cep);
                    clienteController.cadastrarCliente(nome, email, senha, "cliente", endereco, telefone);
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                    dispose();
                    new TelaLogin();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    private class NumericDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string != null && string.matches("\\d+")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text != null && text.matches("\\d+")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }
    }
}