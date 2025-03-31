import java.util.*;
class PlanoFunerario {
    private int id;
    private String nome;
    private double preco;
    private List<String> servicosAdicionais;
    private String formaPagamento;

    public PlanoFunerario(int id, String nome, double preco, String formaPagamento) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.servicosAdicionais = new ArrayList<>();
        this.formaPagamento = formaPagamento;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public List<String> getServicosAdicionais() { return servicosAdicionais; }
    public String getFormaPagamento() { return formaPagamento; }

    public void setNome(String nome) { this.nome = nome; }
    public void setPreco(double preco) { this.preco = preco; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }
    public void adicionarServico(String servico) { servicosAdicionais.add(servico); }
    public void removerServico(String servico) { servicosAdicionais.remove(servico); }

    @Override
    public String toString() {
        return "Plano ID: " + id + ", Nome: " + nome + ", Preço: R$" + preco +
                ", Serviços: " + servicosAdicionais + ", Forma de Pagamento: " + formaPagamento;
    }
}
