package Livraria.Model;

import java.io.IOException;
import java.io.InputStream;

public class Produto {
    private int cod; // PK da tabela
    private String nome;
    private String autor;
    private String isbn; //isbn identificador de livro
    private double preco;
    private int quantidadeEstoque;

    public Produto(int cod, String nome, String autor, String isbn, double preco, int quantidadeEstoque) {
        this.cod = cod;
        this.nome = nome;
        this.autor = autor;
        this.isbn = isbn;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getCod() { return cod; }
    public void setCod(int cod) { this.cod = cod; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }

    public void atualizarEstoque(int quantidade) {
        this.quantidadeEstoque += quantidade;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "cod=" + cod +
                ", nome='" + nome + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                ", preco=" + preco +
                ", quantidadeEstoque=" + quantidadeEstoque +
                '}';
    }
}
