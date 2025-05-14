
package model;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Carne {
    private List<Boleto> boletos = new ArrayList<>();

    public Carne(Cliente cliente) {
        boletos.add(new Boleto(1, LocalDate.now().plusDays(10), 200.0, cliente));
        boletos.add(new Boleto(2, LocalDate.now().plusDays(20), 250.0, cliente));
    }

    public List<Boleto> getBoletos() {
        return boletos;
    }
}
