import java.util.ArrayList;
import java.util.List;

class SistemaFunerario {
    private List<PlanoFunerario> planos;
    private int contadorId;

    public SistemaFunerario() {
        this.planos = new ArrayList<>();
        this.contadorId = 1;
    }

    public void adicionarPlano(String nome, double preco, String formaPagamento) {
        planos.add(new PlanoFunerario(contadorId++, nome, preco, formaPagamento));
        System.out.println("Plano funerário cadastrado com sucesso!");
    }

    public void listarPlanos() {
        if (planos.isEmpty()) {
            System.out.println("Nenhum plano cadastrado.");
        } else {
            for (PlanoFunerario plano : planos) {
                System.out.println(plano);
            }
        }
    }

    public PlanoFunerario buscarPlanoPorId(int id) {
        for (PlanoFunerario plano : planos) {
            if (plano.getId() == id) {
                return plano;
            }
        }
        return null;
    }

    public void atualizarPlano(int id, String novoNome, double novoPreco, String novaFormaPagamento) {
        PlanoFunerario plano = buscarPlanoPorId(id);
        if (plano != null) {
            plano.setNome(novoNome);
            plano.setPreco(novoPreco);
            plano.setFormaPagamento(novaFormaPagamento);
            System.out.println("Plano atualizado!");
        } else {
            System.out.println("Plano não encontrado.");
        }
    }

    public void removerPlano(int id) {
        PlanoFunerario plano = buscarPlanoPorId(id);
        if (plano != null) {
            planos.remove(plano);
            System.out.println("Plano removido.");
        } else {
            System.out.println("Plano não encontrado.");
        }
    }

    public void adicionarServicoAoPlano(int id, String servico) {
        PlanoFunerario plano = buscarPlanoPorId(id);
        if (plano != null) {
            plano.adicionarServico(servico);
            System.out.println("Serviço adicionado ao plano.");
        } else {
            System.out.println("Plano não encontrado.");
        }
    }
}
