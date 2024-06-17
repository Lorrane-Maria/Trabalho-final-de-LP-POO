/*package Livraria.View;

import Livraria.Model.ItemPedido;
import Livraria.Model.Pedido;
import Livraria.Util.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class TelaPagamento extends JFrame {
    private JLabel lblCodigoPix;
    private JButton btnPagar;
    private JComboBox<String> cmbMetodoPagamento;
    private Pedido pedido;

    public TelaPagamento(Pedido pedido) {
        this.pedido = pedido;

        setTitle("Pagamento");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(panel);

        String[] metodosPagamento = {"Pix"};
        cmbMetodoPagamento = new JComboBox<>(metodosPagamento);
        cmbMetodoPagamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCamposPagamento();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Método de pagamento:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(cmbMetodoPagamento, gbc);

        lblCodigoPix = new JLabel();
        lblCodigoPix.setFont(new Font("Arial", Font.PLAIN, 14));
        lblCodigoPix.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(lblCodigoPix, gbc);

        btnPagar = new JButton("Pagar");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(btnPagar, gbc);

        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Realiza o pagamento
                realizarPagamento();

                // Fecha a janela de pagamento
                dispose();
            }
        });

        // Adiciona um WindowListener para detectar o fechamento da janela
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                // Chama o método para deletar os dados do banco de dados
                DatabaseConnection db = new DatabaseConnection();
                try {
                    db.deleteDados();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        atualizarCamposPagamento();

        setVisible(true);
    }

    private void atualizarCamposPagamento() {
        String metodo = (String) cmbMetodoPagamento.getSelectedItem();
        if (metodo.equals("Pix")) {
            gerarCodigoPix();
            lblCodigoPix.setVisible(true);
        } else {
            lblCodigoPix.setVisible(false);
        }
    }

    private void gerarCodigoPix() {
        // Gerar um código Pix aleatório (exemplo simples)
        Random random = new Random();
        long codigo = Math.abs(random.nextLong());
        String codigoPix = String.valueOf(codigo);

        // Exibir o código gerado no JLabel
        lblCodigoPix.setText(codigoPix);
    }

    private void realizarPagamento() {
        // Atualiza o estoque após o pagamento ser realizado
        atualizarEstoque();
        // Exibe mensagem de pagamento realizado com sucesso
        NumberFormat nf = new DecimalFormat("#0.00");
        JOptionPane.showMessageDialog(null, "Pagamento realizado com sucesso! Total: R$ " + nf.format(pedido.getTotal()));
    }

    private void atualizarEstoque() {
        // Atualiza a quantidade no estoque subtraindo a quantidade do item no pedido
        for (ItemPedido item : pedido.getItens()) {
            int produtoCod = item.getProduto().getCod();
            int quantidadePedido = item.getQuantidade();

            try (Connection conn = DatabaseConnection.getConnection()) {
                // Atualiza a tabela estoque
                try (PreparedStatement stmtEstoque = conn.prepareStatement("UPDATE estoque SET quantidade = quantidade - ? WHERE produto_cod = ?")) {
                    stmtEstoque.setInt(1, quantidadePedido);
                    stmtEstoque.setInt(2, produtoCod);
                    stmtEstoque.executeUpdate();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque: " + e.getMessage());
                }

                // Atualiza a tabela produto para refletir a quantidade do estoque
                try (PreparedStatement stmtProduto = conn.prepareStatement("UPDATE produto SET quantidade = (SELECT quantidade FROM estoque WHERE produto_cod = ?) WHERE cod = ?")) {
                    stmtProduto.setInt(1, produtoCod);
                    stmtProduto.setInt(2, produtoCod);
                    stmtProduto.executeUpdate();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar quantidade do produto: " + e.getMessage());
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados: " + e.getMessage());
            }
        }
    }
}*/

/*package Livraria.View;

import Livraria.Model.ItemPedido;
import Livraria.Model.Pedido;
import Livraria.Util.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class TelaPagamento extends JFrame {
    private JLabel lblCodigoPix;
    private JButton btnPagar;
    private JComboBox<String> cmbMetodoPagamento;
    private Pedido pedido;

    public TelaPagamento(Pedido pedido) {
        this.pedido = pedido;

        setTitle("Pagamento");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(panel);

        // Título
        JLabel lblTitulo = new JLabel("Informação de pagamento");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(0, 77, 64));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitulo, gbc);

        // Métodos de pagamento
        String[] metodosPagamento = {"Pix"};
        cmbMetodoPagamento = new JComboBox<>(metodosPagamento);
        cmbMetodoPagamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCamposPagamento();
            }
        });

        JLabel lblMetodoPagamento = new JLabel("Método de pagamento:");
        lblMetodoPagamento.setForeground(new Color(0, 77, 64));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblMetodoPagamento, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(cmbMetodoPagamento, gbc);

        lblCodigoPix = new JLabel();
        lblCodigoPix.setFont(new Font("Arial", Font.PLAIN, 14));
        lblCodigoPix.setForeground(new Color(0, 77, 64));
        lblCodigoPix.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(lblCodigoPix, gbc);

        btnPagar = new JButton("Pagar");
        btnPagar.setBackground(new Color(203, 166, 176));
        btnPagar.setForeground(new Color(0, 77, 64));
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(btnPagar, gbc);

        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Realiza o pagamento
                realizarPagamento();

                // Fecha a janela de pagamento
                dispose();
            }
        });

        // Adiciona um WindowListener para detectar o fechamento da janela
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                // Chama o método para deletar os dados do banco de dados
                DatabaseConnection db = new DatabaseConnection();
                try {
                    db.deleteDados();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        atualizarCamposPagamento();

        setVisible(true);
    }

    private void atualizarCamposPagamento() {
        String metodo = (String) cmbMetodoPagamento.getSelectedItem();
        if (metodo.equals("Pix")) {
            gerarCodigoPix();
            lblCodigoPix.setVisible(true);
        } else {
            lblCodigoPix.setVisible(false);
        }
    }

    private void gerarCodigoPix() {
        // Gerar um código Pix aleatório (exemplo simples)
        Random random = new Random();
        long codigo = Math.abs(random.nextLong());
        String codigoPix = String.valueOf(codigo);

        // Exibir o código gerado no JLabel
        lblCodigoPix.setText(codigoPix);
    }

    private void realizarPagamento() {
        // Atualiza o estoque após o pagamento ser realizado
        atualizarEstoque();
        // Exibe mensagem de pagamento realizado com sucesso
        NumberFormat nf = new DecimalFormat("#0.00");
        JOptionPane.showMessageDialog(null, "Pagamento realizado com sucesso! Total: R$ " + nf.format(pedido.getTotal()));
    }

    private void atualizarEstoque() {
        // Atualiza a quantidade no estoque subtraindo a quantidade do item no pedido
        for (ItemPedido item : pedido.getItens()) {
            int produtoCod = item.getProduto().getCod();
            int quantidadePedido = item.getQuantidade();

            try (Connection conn = DatabaseConnection.getConnection()) {
                // Atualiza a tabela estoque
                try (PreparedStatement stmtEstoque = conn.prepareStatement("UPDATE estoque SET quantidade = quantidade - ? WHERE produto_cod = ?")) {
                    stmtEstoque.setInt(1, quantidadePedido);
                    stmtEstoque.setInt(2, produtoCod);
                    stmtEstoque.executeUpdate();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque: " + e.getMessage());
                }

                // Atualiza a tabela produto para refletir a quantidade do estoque
                try (PreparedStatement stmtProduto = conn.prepareStatement("UPDATE produto SET quantidade = (SELECT quantidade FROM estoque WHERE produto_cod = ?) WHERE cod = ?")) {
                    stmtProduto.setInt(1, produtoCod);
                    stmtProduto.setInt(2, produtoCod);
                    stmtProduto.executeUpdate();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar quantidade do produto: " + e.getMessage());
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados: " + e.getMessage());
            }
        }
    }
}*/

package Livraria.View;

import Livraria.Model.ItemPedido;
import Livraria.Model.Pedido;
import Livraria.Util.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class TelaPagamento extends JFrame {
    private JLabel lblCodigoPix;
    private JLabel lblNumeroCartao;
    private JLabel lblNomeTitular;
    private JLabel lblValidade;
    private JLabel lblCVV;
    private JTextField txtNumeroCartao;
    private JTextField txtNomeTitular;
    private JTextField txtValidade;
    private JTextField txtCVV;
    private JButton btnPagar;
    private JComboBox<String> cmbMetodoPagamento;
    private Pedido pedido;

    public TelaPagamento(Pedido pedido) {
        this.pedido = pedido;

        setTitle("Pagamento");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(panel);

        // Título
        JLabel lblTitulo = new JLabel("Informação de pagamento");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(0, 77, 64));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitulo, gbc);

        // Métodos de pagamento
        String[] metodosPagamento = {"Pix", "Cartão de Crédito", "Cartão de Débito"};
        cmbMetodoPagamento = new JComboBox<>(metodosPagamento);
        cmbMetodoPagamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCamposPagamento();
            }
        });

        JLabel lblMetodoPagamento = new JLabel("Método de pagamento:");
        lblMetodoPagamento.setForeground(new Color(0, 77, 64));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblMetodoPagamento, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(cmbMetodoPagamento, gbc);

        // Campos para Pix
        lblCodigoPix = new JLabel();
        lblCodigoPix.setFont(new Font("Arial", Font.PLAIN, 14));
        lblCodigoPix.setForeground(new Color(0, 77, 64));
        lblCodigoPix.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(lblCodigoPix, gbc);

        // Campos para cartão de crédito e débito
        lblNumeroCartao = new JLabel("Número do cartão:");
        lblNumeroCartao.setForeground(new Color(0, 77, 64));
        txtNumeroCartao = new JTextField(16);

        lblNomeTitular = new JLabel("Nome do titular:");
        lblNomeTitular.setForeground(new Color(0, 77, 64));
        txtNomeTitular = new JTextField(30);

        lblValidade = new JLabel("Validade (MM/AA):");
        lblValidade.setForeground(new Color(0, 77, 64));
        txtValidade = new JTextField(5);

        lblCVV = new JLabel("CVV:");
        lblCVV.setForeground(new Color(0, 77, 64));
        txtCVV = new JTextField(3);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblNumeroCartao, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(txtNumeroCartao, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblNomeTitular, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(txtNomeTitular, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(lblValidade, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(txtValidade, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(lblCVV, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(txtCVV, gbc);

        // Botão de pagamento
        btnPagar = new JButton("Pagar");
        btnPagar.setBackground(new Color(203, 166, 176));
        btnPagar.setForeground(new Color(0, 77, 64));
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(btnPagar, gbc);

        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Realiza o pagamento
                realizarPagamento();

                // Fecha a janela de pagamento
                dispose();
            }
        });

        // Adiciona um WindowListener para detectar o fechamento da janela
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                // Chama o método para deletar os dados do banco de dados
                DatabaseConnection db = new DatabaseConnection();
                try {
                    db.deleteDados();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        atualizarCamposPagamento();

        setVisible(true);
    }

    private void atualizarCamposPagamento() {
        String metodo = (String) cmbMetodoPagamento.getSelectedItem();
        boolean isPix = metodo.equals("Pix");
        boolean isCartao = metodo.equals("Cartão de Crédito") || metodo.equals("Cartão de Débito");

        lblCodigoPix.setVisible(isPix);

        lblNumeroCartao.setVisible(isCartao);
        txtNumeroCartao.setVisible(isCartao);
        lblNomeTitular.setVisible(isCartao);
        txtNomeTitular.setVisible(isCartao);
        lblValidade.setVisible(isCartao);
        txtValidade.setVisible(isCartao);
        lblCVV.setVisible(isCartao);
        txtCVV.setVisible(isCartao);

        if (isPix) {
            gerarCodigoPix();
        }
    }

    private void gerarCodigoPix() {
        // Gerar um código Pix aleatório (exemplo simples)
        Random random = new Random();
        long codigo = Math.abs(random.nextLong());
        String codigoPix = String.valueOf(codigo);

        // Exibir o código gerado no JLabel
        lblCodigoPix.setText(codigoPix);
    }

    private void realizarPagamento() {
        // Atualiza o estoque após o pagamento ser realizado
        atualizarEstoque();
        // Exibe mensagem de pagamento realizado com sucesso
        NumberFormat nf = new DecimalFormat("#0.00");
        JOptionPane.showMessageDialog(null, "Pagamento realizado com sucesso! Total: R$ " + nf.format(pedido.getTotal()));
    }

    private void atualizarEstoque() {
        // Atualiza a quantidade no estoque subtraindo a quantidade do item no pedido
        for (ItemPedido item : pedido.getItens()) {
            int produtoCod = item.getProduto().getCod();
            int quantidadePedido = item.getQuantidade();

            try (Connection conn = DatabaseConnection.getConnection()) {
                // Atualiza a tabela estoque
                try (PreparedStatement stmtEstoque = conn.prepareStatement("UPDATE estoque SET quantidade = quantidade - ? WHERE produto_cod = ?")) {
                    stmtEstoque.setInt(1, quantidadePedido);
                    stmtEstoque.setInt(2, produtoCod);
                    stmtEstoque.executeUpdate();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque: " + e.getMessage());
                }

                // Atualiza a tabela produto para refletir a quantidade do estoque
                try (PreparedStatement stmtProduto = conn.prepareStatement("UPDATE produto SET quantidade = (SELECT quantidade FROM estoque WHERE produto_cod = ?) WHERE cod = ?")) {
                    stmtProduto.setInt(1, produtoCod);
                    stmtProduto.setInt(2, produtoCod);
                    stmtProduto.executeUpdate();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar quantidade do produto: " + e.getMessage());
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados: " + e.getMessage());
            }
        }
    }
}

