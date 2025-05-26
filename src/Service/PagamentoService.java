package Service;

public class PagamentoService {

    // Simulação de um pagamento via cartão
    public String realizarPagamentoCartao(double valor) {
        // Simulação de chamada à API de pagamento
        System.out.println("Pagamento de R$" + valor + " realizado via cartão de crédito.");
        return "Pagamento aprovado";
    }

    // Simulação de um pagamento via Pix
    public String realizarPagamentoPix(double valor) {
        // Simulação de chamada à API de pagamento
        System.out.println("Pagamento de R$" + valor + " realizado via Pix.");
        return "Pagamento aprovado";
    }
}
