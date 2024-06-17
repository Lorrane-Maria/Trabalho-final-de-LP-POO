package Livraria.Controller;

import Livraria.Model.Cliente;
import Livraria.Model.ItemPedido;
import Livraria.Model.Pedido;

import java.util.List;

//Cria pedido
public class PedidoController {
    public Pedido criarPedido(Cliente cliente, List<ItemPedido> itens, String data) {
        return new Pedido(cliente, itens, data);
    }
}
