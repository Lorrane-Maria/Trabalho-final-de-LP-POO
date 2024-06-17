package Livraria.View;

import Livraria.Controller.ProdutoController;
import Livraria.Model.Cliente;
import Livraria.Model.ItemPedido;
import Livraria.Model.Produto;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelaVitrine extends JFrame {
    private JTable tblProdutos;
    private JButton btnAdicionarCarrinho;
    private JButton btnVerCarrinho;
    private Cliente cliente;
    private List<Produto> produtosDisponiveis;
    private List<ItemPedido> itensSelecionados;

    public TelaVitrine(Cliente cliente) {
        this(cliente, null); // Chamando o construtor com produtosDisponiveis = null
    }

    public TelaVitrine(Cliente cliente, List<Produto> produtosAtualizados) {
        this.cliente = cliente;
        this.itensSelecionados = new ArrayList<>();

        setTitle("Vitrine de Produtos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GradientPanel mainPanel = new GradientPanel(Color.decode("#F5F5F5"), Color.decode("#FFFFFF"));
        mainPanel.setLayout(new BorderLayout());

        tblProdutos = new JTable();
        if (produtosAtualizados != null) {
            this.produtosDisponiveis = produtosAtualizados; // Usar produtos atualizados se fornecidos
        } else {
            this.produtosDisponiveis = carregarProdutosDisponiveis(); // Senão, carregar produtos iniciais
        }
        atualizarTabelaProdutos();

        btnAdicionarCarrinho = new RoundedButton("Adicionar ao Carrinho");
        btnAdicionarCarrinho.setBackground(Color.decode("#a5c6c0")); // Cor desejada
        btnAdicionarCarrinho.setForeground(Color.decode("#004D40"));
        btnAdicionarCarrinho.setBorder(BorderFactory.createEmptyBorder());
        btnAdicionarCarrinho.setPreferredSize(new Dimension(200, 50)); // Tamanho maior

        btnVerCarrinho = new RoundedButton("Ver Carrinho");
        btnVerCarrinho.setBackground(Color.decode("#a5c6c0")); // Cor desejada
        btnVerCarrinho.setForeground(Color.decode("#004D40"));
        btnVerCarrinho.setBorder(BorderFactory.createEmptyBorder());
        btnVerCarrinho.setPreferredSize(new Dimension(200, 50)); // Tamanho maior

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false); // Tornar o painel transparente
        buttonPanel.add(btnAdicionarCarrinho);
        buttonPanel.add(btnVerCarrinho);

        mainPanel.add(new JScrollPane(tblProdutos), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Ação do botão Ver Carrinho
        btnVerCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaCarrinho();
            }
        });

        // Ação do botão Adicionar ao Carrinho
        btnAdicionarCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = tblProdutos.getSelectedRow();
                if (rowIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um produto para adicionar ao carrinho.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int quantidade = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a quantidade desejada:", "Quantidade", JOptionPane.QUESTION_MESSAGE));
                if (quantidade <= 0) {
                    JOptionPane.showMessageDialog(null, "Quantidade inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Produto produtoSelecionado = produtosDisponiveis.get(rowIndex);
                if (quantidade > produtoSelecionado.getQuantidadeEstoque()) {
                    JOptionPane.showMessageDialog(null, "Quantidade desejada maior que a disponível em estoque.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Criar um ItemPedido com base no produto selecionado e na quantidade
                ItemPedido itemPedido = new ItemPedido(produtoSelecionado, quantidade, produtoSelecionado.getPreco());
                itensSelecionados.add(itemPedido);

                JOptionPane.showMessageDialog(null, "Produto '" + produtoSelecionado.getNome() + "' adicionado ao carrinho.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private List<Produto> carregarProdutosDisponiveis() {
        ProdutoController produtoController = new ProdutoController();
        try {
            return produtoController.listarProdutosComEstoque();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar produtos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>(); // Retorna lista vazia em caso de erro
        }
    }

    private void atualizarTabelaProdutos() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Autor");
        model.addColumn("ISBN");
        model.addColumn("Preço");
        model.addColumn("Quantidade");

        for (Produto produto : produtosDisponiveis) {
            Object[] row = {
                    produto.getNome(),
                    produto.getAutor(),
                    produto.getIsbn(),
                    produto.getPreco(),
                    produto.getQuantidadeEstoque()
            };
            model.addRow(row);
        }

        tblProdutos.setModel(model);

        // Ajustar aparência da tabela
        tblProdutos.setRowHeight(30);
        tblProdutos.setFont(new Font("Arial", Font.PLAIN, 16));
        tblProdutos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        tblProdutos.getTableHeader().setBackground(Color.decode("#CCCCFF"));
        tblProdutos.getTableHeader().setForeground(Color.decode("#333333"));
    }

    private void abrirTelaCarrinho() {
        new TelaCarrinho(cliente, itensSelecionados).setVisible(true);
        dispose();
    }

    // Classe GradientPanel para criar o painel de fundo gradiente
    private class GradientPanel extends JPanel {
        private final Color startColor;
        private final Color endColor;

        public GradientPanel(Color startColor, Color endColor) {
            this.startColor = startColor;
            this.endColor = endColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int w = getWidth();
            int h = getHeight();
            GradientPaint gp = new GradientPaint(0, 0, startColor, 0, h, endColor);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
    }

    // Classe RoundedButton para criar botões arredondados
    private class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            super.paintComponent(g2d);
            g2d.dispose();
        }

        @Override
        public void updateUI() {
            setUI(new BasicButtonUI());
        }

        @Override
        protected void paintBorder(Graphics g) {
            // No border painting
        }

        @Override
        public boolean contains(int x, int y) {
            int r = 30;
            return new Ellipse2D.Float(0, 0, getWidth(), getHeight()).contains(x, y);
        }
    }
}

