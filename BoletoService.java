package service;

import model.Boleto;
import model.Carne;
import model.Cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoletoService {
    private List<Boleto> boletos = new ArrayList<>();  // Lista fictícia de boletos para exemplificar

    // Método para gerar carnê (como já existe)
    public Carne gerarCarne(Cliente cliente, double valor, int parcelas, LocalDate dataInicial) {
        return new Carne(cliente);
    }

    // Método para marcar boleto como pago
    public void marcarComoPago(int idBoleto) {
        System.out.println("Boleto " + idBoleto + " marcado como pago.");
    }

    // Implementação do método listarInadimplentes
    public List<Boleto> listarInadimplentes() {
        List<Boleto> inadimplentes = new ArrayList<>();

        // Percorre todos os boletos e verifica os que estão vencidos e não pagos
        for (Boleto b : boletos) {
            if (b.getVencimento().isBefore(LocalDate.now()) && !b.isPago()) {
                inadimplentes.add(b);
            }
        }

        return inadimplentes;
    }

    // Adicionar um boleto à lista para fins de teste
    public void adicionarBoleto(Boleto boleto) {
        boletos.add(boleto);
    }
}

