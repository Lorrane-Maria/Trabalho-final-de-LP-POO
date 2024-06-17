/*package Livraria.View;

import Livraria.Controller.PedidoController;
import Livraria.Model.Cliente;
import Livraria.Model.ItemPedido;
import Livraria.Model.Pedido;
import Livraria.Model.Produto;
import Livraria.Controller.ProdutoController;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class TelaCarrinho extends JFrame {
    private JTable tblCarrinho;
    private JButton btnFinalizarCompra;
    private JButton btnContinuarComprando;
    private JLabel lblSubtotal;
    private Cliente cliente;
    private List<ItemPedido> itens;

    public TelaCarrinho(Cliente cliente, List<ItemPedido> itens) {
        this.cliente = cliente;
        this.itens = itens;

        setTitle("Carrinho de Compras");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);

        // Tabela
        tblCarrinho = new JTable();
        JScrollPane scrollPane = new JScrollPane(tblCarrinho);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Modelo da tabela
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Produto", "Preço unitário", "Quantidade"}, 0);
        for (ItemPedido item : itens) {
            model.addRow(new Object[]{item.getProduto().getNome(), item.getProduto().getPreco(), item.getQuantidade()});
        }
        tblCarrinho.setModel(model);

        // Painel inferior
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        lblSubtotal = new JLabel("Subtotal: R$ 0,00");
        gbc.gridx = 1;
        gbc.gridy = 1;
        bottomPanel.add(lblSubtotal, gbc);

        btnContinuarComprando = new JButton("Continuar comprando");
        gbc.gridx = 0;
        gbc.gridy = 2;
        bottomPanel.add(btnContinuarComprando, gbc);

        btnFinalizarCompra = new JButton("Finalizar compra");
        gbc.gridx = 2;
        gbc.gridy = 2;
        bottomPanel.add(btnFinalizarCompra, gbc);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        btnFinalizarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PedidoController pedidoController = new PedidoController();
                Pedido pedido = pedidoController.criarPedido(cliente, itens, "27/05/2024");
                JOptionPane.showMessageDialog(null, "Compra finalizada com sucesso! Total: " + pedido.getTotal());
                dispose();
                new TelaPagamento(pedido);
            }
        });

        btnContinuarComprando.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                abrirTelaVitrine();
            }
        });

        calcularSubtotal();

        setVisible(true);
    }

    private void calcularSubtotal() {
        double subtotal = 0;
        for (ItemPedido item : itens) {
            subtotal += item.getProduto().getPreco() * item.getQuantidade();
        }
        lblSubtotal.setText("Subtotal: R$ " + String.format("%.2f", subtotal));
    }

    private void abrirTelaVitrine() {
        // Atualizar os produtos disponíveis antes de abrir a TelaVitrine
        List<Produto> produtosAtualizados = carregarProdutosAtualizados();

        // Abrir a TelaVitrine com os produtos atualizados
        new TelaVitrine(cliente, produtosAtualizados).setVisible(true);
    }

    private List<Produto> carregarProdutosAtualizados() {
        ProdutoController produtoController = new ProdutoController();
        try {
            return produtoController.listarProdutosComEstoque();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar produtos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}*/

package Livraria.View;

import Livraria.Controller.PedidoController;
import Livraria.Model.Cliente;
import Livraria.Model.ItemPedido;
import Livraria.Model.Pedido;
import Livraria.Model.Produto;
import Livraria.Controller.ProdutoController;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class TelaCarrinho extends JFrame {
    private JTable tblCarrinho;
    private JButton btnFinalizarCompra;
    private JButton btnContinuarComprando;
    private JLabel lblSubtotal;
    private Cliente cliente;
    private List<ItemPedido> itens;

    public TelaCarrinho(Cliente cliente, List<ItemPedido> itens) {
        this.cliente = cliente;
        this.itens = itens;

        setTitle("Carrinho de Compras");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);

        // Tabela
        tblCarrinho = new JTable();
        JScrollPane scrollPane = new JScrollPane(tblCarrinho);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Modelo da tabela
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Produto", "Preço unitário", "Quantidade"}, 0);
        for (ItemPedido item : itens) {
            model.addRow(new Object[]{item.getProduto().getNome(), item.getProduto().getPreco(), item.getQuantidade()});
        }
        tblCarrinho.setModel(model);

        // Alterar a cor do texto da tabela
        tblCarrinho.setForeground(new Color(0, 77, 64));

        // Painel inferior
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        lblSubtotal = new JLabel("Subtotal: R$ 0,00");
        lblSubtotal.setForeground(new Color(0, 77, 64));
        gbc.gridx = 1;
        gbc.gridy = 1;
        bottomPanel.add(lblSubtotal, gbc);

        btnContinuarComprando = new JButton("Desistir");
        btnContinuarComprando.setBackground(Color.decode("#cba6b0"));
        btnContinuarComprando.setForeground(Color.decode("#004D40"));
        gbc.gridx = 0;
        gbc.gridy = 2;
        bottomPanel.add(btnContinuarComprando, gbc);

        btnFinalizarCompra = new JButton("Finalizar compra");
        btnFinalizarCompra.setBackground(Color.decode("#cba6b0"));
        btnFinalizarCompra.setForeground(Color.decode("#004D40"));
        gbc.gridx = 2;
        gbc.gridy = 2;
        bottomPanel.add(btnFinalizarCompra, gbc);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        btnFinalizarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PedidoController pedidoController = new PedidoController();
                Pedido pedido = pedidoController.criarPedido(cliente, itens, "27/05/2024");
                JOptionPane.showMessageDialog(null, "Compra finalizada com sucesso! Total: " + pedido.getTotal());
                dispose();
                new TelaPagamento(pedido);
            }
        });

        btnContinuarComprando.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                abrirTelaVitrine();
            }
        });

        calcularSubtotal();

        setVisible(true);
    }

    private void calcularSubtotal() {
        double subtotal = 0;
        for (ItemPedido item : itens) {
            subtotal += item.getProduto().getPreco() * item.getQuantidade();
        }
        lblSubtotal.setText("Subtotal: R$ " + String.format("%.2f", subtotal));
    }

    private void abrirTelaVitrine() {
        // Atualizar os produtos disponíveis antes de abrir a TelaVitrine
        List<Produto> produtosAtualizados = carregarProdutosAtualizados();

        // Abrir a Tela Vitrine com os produtos atualizados
        new TelaVitrine(cliente, produtosAtualizados).setVisible(true);
    }

    private List<Produto> carregarProdutosAtualizados() {
        ProdutoController produtoController = new ProdutoController();
        try {
            return produtoController.listarProdutosComEstoque();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar produtos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}

