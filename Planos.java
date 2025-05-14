package model;

public class Planos {
    private int id;
    private String nome;
    private double preco;
    private String formaPagamento;

    // Construtor
    public Planos(int id, String nome, double preco, String formaPagamento) {
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
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
