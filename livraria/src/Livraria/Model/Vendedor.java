package Livraria.Model;

public class Vendedor extends Usuario {
    private Endereco endereco;
    private String telefone;
    private int cod;

    public Vendedor(String tipo, String nome, String email, String senha, Endereco endereco, String telefone) {
        super(nome, email, senha, "vendedor");
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    @Override
    public String toString() {
        return "Vendedor{" +
                "nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", endereco=" + endereco +
                ", senha='" + getSenha() + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}

