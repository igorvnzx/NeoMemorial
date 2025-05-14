
package service;

import model.Planos;
import java.util.ArrayList;
import java.util.List;

public class SistemaFunerario {
    private List<Planos> planos = new ArrayList<>();  // Lista de planos cadastrados
    private int idCounter = 1;  // Contador de IDs dos planos

    // Adicionar um novo plano
    public void adicionarPlano(String nome, double preco, String formaPagamento) {
        Planos plano = new Planos(idCounter++, nome, preco, formaPagamento);
        planos.add(plano);
    }

    // Listar todos os planos
    public List<Planos> listarPlanos() {
        return planos;
    }

    // Atualizar um plano existente
    public void atualizarPlano(int id, String nome, double preco, String formaPagamento) {
        for (Planos plano : planos) {
            if (plano.getId() == id) {
                plano.setNome(nome);
                plano.setPreco(preco);
                plano.setFormaPagamento(formaPagamento);
                break;
            }
        }
    }

    // Remover um plano
    public void removerPlano(int id) {
        planos.removeIf(plano -> plano.getId() == id);
    }
}
