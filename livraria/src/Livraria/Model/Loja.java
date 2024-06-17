package Livraria.Model;

import java.util.List;

public class Loja {
    private String nome;
    private String endereco;
    private List<Produto> produtos;

    public Loja(String nome, String endereco, List<Produto> produtos) {
        this.nome = nome;
        this.endereco = endereco;
        this.produtos = produtos;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public List<Produto> getProdutos() { return produtos; }
    public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }


    @Override
    public String toString() {
        return "Loja{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", produtos=" + produtos +
                '}';
    }
}

