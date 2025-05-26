package model;

public class Plano {
    private int id;
    private String nome;
    private double preco;
    private String formaPagamento;

    // Construtor
    public Plano() {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.formaPagamento = formaPagamento;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void setId(int id) {
    }

    public void setDescricao(String descricao) {
    }

    public String getDescricao() {
        return null;
    }
}
