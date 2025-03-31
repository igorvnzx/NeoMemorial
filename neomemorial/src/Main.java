import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaFunerario sistema = new SistemaFunerario();

        while (true) {
            System.out.println("\n--- Sistema Funerário ---");
            System.out.println("1. Cadastrar Plano");
            System.out.println("2. Listar Planos");
            System.out.println("3. Atualizar Plano");
            System.out.println("4. Remover Plano");
            System.out.println("5. Adicionar Serviço ao Plano");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do Plano: ");
                    String nome = scanner.nextLine();
                    System.out.print("Preço do Plano: ");
                    double preco = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Forma de Pagamento: ");
                    String formaPagamento = scanner.nextLine();
                    sistema.adicionarPlano(nome, preco, formaPagamento);
                    break;
                case 2:
                    sistema.listarPlanos();
                    break;
                case 3:
                    System.out.print("ID do Plano a Atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo Nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo Preço: ");
                    double novoPreco = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Nova Forma de Pagamento: ");
                    String novaFormaPagamento = scanner.nextLine();
                    sistema.atualizarPlano(idAtualizar, novoNome, novoPreco, novaFormaPagamento);
                    break;
                case 4:
                    System.out.print("ID do Plano a Remover: ");
                    int idRemover = scanner.nextInt();
                    sistema.removerPlano(idRemover);
                    break;
                case 5:
                    System.out.print("ID do Plano: ");
                    int idServico = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome do Serviço: ");
                    String servico = scanner.nextLine();
                    sistema.adicionarServicoAoPlano(idServico, servico);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
