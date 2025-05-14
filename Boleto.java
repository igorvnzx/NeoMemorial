
package model;

import java.time.LocalDate;

public class Boleto {
    private int id;
    private LocalDate vencimento;
    private double valor;
    private boolean pago;
    private Cliente cliente;

    public Boleto(int id, LocalDate vencimento, double valor, Cliente cliente) {
        this.id = id;
        this.vencimento = vencimento;
        this.valor = valor;
        this.cliente = cliente;
        this.pago = false;
    }

    public int getId() {
        return id;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public double getValor() {
        return valor;
    }

    public boolean isPago() {
        return pago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void pagar() {
        this.pago = true;
    }
}
