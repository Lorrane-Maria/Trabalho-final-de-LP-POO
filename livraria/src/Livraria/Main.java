package Livraria;

import Livraria.Controller.ClienteController;
import Livraria.Controller.PedidoController;
import Livraria.Controller.ProdutoController;
import Livraria.Model.Cliente;
import Livraria.Model.Endereco;
import Livraria.Model.ItemPedido;
import Livraria.Model.Pedido;
import Livraria.Model.Produto;
import Livraria.Util.DatabaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection.createTablesIfNotExists();
        DatabaseConnection.insertValuesInTables();
        ClienteController clienteController = new ClienteController();
        ProdutoController produtoController = new ProdutoController();
        PedidoController pedidoController = new PedidoController();

        Scanner scanner = new Scanner(System.in);

        // Simulação de cadastro de cliente
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();
        System.out.print("Digite sua rua: ");
        String rua = scanner.nextLine();
        System.out.print("Digite seu numero: ");
        String numero = scanner.nextLine();
        System.out.print("Digite seu bairro: ");
        String bairro = scanner.nextLine();
        System.out.print("Digite sua cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Digite seu estado: ");
        String estado = scanner.nextLine();
        System.out.print("Digite seu cep: ");
        String cep = scanner.nextLine();
        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado, cep);
        System.out.print("Digite seu telefone: ");
        String telefone = scanner.nextLine();

        try {
            clienteController.cadastrarCliente(nome, email, senha, "cliente", endereco, telefone);
            System.out.println("Cliente cadastrado com sucesso!");

            // Simulação de recuperação do cliente recém-cadastrado
            // Supondo que o ID do cliente foi armazenado no controlador após a inserção
            Cliente cliente = new Cliente(nome, email, senha, "cliente", endereco, telefone); // Preencha os campos de acordo com sua lógica
            System.out.println("Cliente recuperado: " + cliente);
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
        }

        // Adicionar produtos ao estoque
        Produto livro1 = new Produto(1, "É assim que acaba", "Colleen Hoover", "978-85-01-11251-4", 45.80, 10);
        Produto livro2 = new Produto(2, "Anne de Green Glables", "L. M. Montgomery", "978-85-61635-06-0", 50.00, 10);
        produtoController.adicionarProdutoEstoque(livro1, 10);
        produtoController.adicionarProdutoEstoque(livro2, 5);

        // Criar pedido
        List<ItemPedido> itens = new ArrayList<>();
        itens.add(new ItemPedido(livro1, 2, livro1.getPreco()));
        itens.add(new ItemPedido(livro2, 1, livro2.getPreco()));

        Cliente cliente = new Cliente(nome, email, senha, "cliente", endereco, telefone);
        Pedido pedido = pedidoController.criarPedido(cliente, itens, "27/05/2024");
        System.out.println("Pedido criado com sucesso! Total: " + pedido.getTotal());

        scanner.close();
    }
}
