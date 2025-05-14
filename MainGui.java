package ui;

import service.*;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class MainGui {
    // Instância global do SistemaFunerario
    private static SistemaFunerario sistemaFunerario = new SistemaFunerario();

    public static void main(String[] args) {
        // Criar e exibir a janela
        JFrame frame = new JFrame("Escolha o Sistema");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);  // Centraliza a janela

        // Criar o painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // Layout vertical
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Adiciona margens

        // Adicionar título
        JLabel titleLabel = new JLabel("Escolha o sistema que deseja acessar:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        // Botões para escolher o sistema
        JButton sistemaFinanceiroButton = new JButton("Sistema Financeiro");
        sistemaFinanceiroButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sistemaFinanceiroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarSistemaFinanceiro(frame);
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(sistemaFinanceiroButton);

        JButton sistemaFunerarioButton = new JButton("Sistema Funerário");
        sistemaFunerarioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sistemaFunerarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarSistemaFunerario(frame);
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(sistemaFunerarioButton);

        // Botão de sair
        JButton sairButton = new JButton("Sair");
        sairButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Fecha o programa
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(sairButton);

        // Exibir o painel
        frame.add(panel);
        frame.setVisible(true);
    }

    // Método para exibir o sistema financeiro
    private static void mostrarSistemaFinanceiro(JFrame frame) {
        // Criar painel do sistema financeiro
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Sistema Financeiro: Gerar Carnê");
        panel.add(label);

        // Exemplo de campo de entrada para cliente
        JTextField nomeField = new JTextField("Igor");
        nomeField.setMaximumSize(new Dimension(200, 30));
        panel.add(nomeField);

        // Adicionar botão para gerar carnê
        JButton gerarCarneButton = new JButton("Gerar Carnê");
        gerarCarneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = new Cliente(1, nomeField.getText(), "igor@email.com");
                BoletoService boletoService = new BoletoService();
                Carne carne = boletoService.gerarCarne(cliente, 200.0, 3, LocalDate.now().minusDays(10));
                JOptionPane.showMessageDialog(frame, "Carnê gerado para " + cliente.getNome());

                // Após gerar o carnê, adicionar opções de pagamento e mostrar boletos
                mostrarOpcoesDePagamento(frame, carne, boletoService);
            }
        });
        panel.add(gerarCarneButton);

        // Botão de sair
        JButton sairButton = new JButton("Sair");
        sairButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Fecha o programa
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(sairButton);

        // Adicionar o painel à janela
        frame.setContentPane(panel);  // Atualiza o painel com o novo conteúdo
        frame.revalidate();
        frame.repaint();
    }

    // Método para mostrar as opções de pagamento e inadimplência após gerar o carnê
    private static void mostrarOpcoesDePagamento(JFrame frame, Carne carne, BoletoService boletoService) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Escolha a opção:");
        panel.add(label);

        // Botão para pagar boleto
        JButton pagarBoletoButton = new JButton("Pagar Boleto");
        pagarBoletoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String metodo = JOptionPane.showInputDialog("Escolha o método de pagamento (pix/cartao): ");
                int idBoleto = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do boleto a ser pago:"));

                boletoService.marcarComoPago(idBoleto);
                JOptionPane.showMessageDialog(frame, "Pagamento realizado com sucesso!");

                // Exibir boletos inadimplentes
                mostrarBoletosInadimplentes(frame, boletoService);
            }
        });
        panel.add(pagarBoletoButton);

        // Botão de sair
        JButton sairButton = new JButton("Sair");
        sairButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Fecha o programa
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(sairButton);

        // Adicionar o painel à janela
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }

    // Método para exibir boletos inadimplentes
    private static void mostrarBoletosInadimplentes(JFrame frame, BoletoService boletoService) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Boletos Inadimplentes:");
        panel.add(label);

        // Obter a lista de boletos inadimplentes
        java.util.List<Boleto> boletosInadimplentes = boletoService.listarInadimplentes();

        // Verificar se há boletos inadimplentes
        if (boletosInadimplentes.isEmpty()) {
            // Se não houver boletos, exibe a mensagem "Nenhuma Pendência"
            panel.add(new JLabel("Nenhuma Pendência"));
        } else {
            // Se houver boletos, exibe cada um deles
            for (Boleto b : boletosInadimplentes) {
                panel.add(new JLabel("Boleto ID: " + b.getId() + " - Cliente: " + b.getCliente().getNome() + " - Vencimento: " + b.getVencimento()));
            }
        }

        // Botão de sair
        JButton sairButton = new JButton("Sair");
        sairButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Fecha o programa
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(sairButton);

        // Adicionar o painel à janela
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }

    // Método para exibir o sistema funerário com funcionalidades completas
    private static void mostrarSistemaFunerario(JFrame frame) {
        // Instância única do SistemaFunerario
        SistemaFunerario sistema = sistemaFunerario;

        JFrame funFrame = new JFrame("Sistema Funerário");
        funFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        funFrame.setSize(500, 400);
        funFrame.setLocationRelativeTo(null);  // Centraliza a janela

        // Criar painel e componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Sistema Funerário: Escolha uma opção");
        panel.add(label);

        // Botão para adicionar plano
        JButton adicionarPlanoButton = new JButton("Adicionar Plano");
        adicionarPlanoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomePlano = JOptionPane.showInputDialog("Nome do Plano:");
                double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço do Plano:"));
                String formaPagamento = JOptionPane.showInputDialog("Forma de Pagamento:");
                sistema.adicionarPlano(nomePlano, preco, formaPagamento);
                JOptionPane.showMessageDialog(funFrame, "Plano '" + nomePlano + "' adicionado.");
            }
        });
        panel.add(adicionarPlanoButton);

        // Botão para listar planos
        JButton listarPlanosButton = new JButton("Listar Planos");
        listarPlanosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Planos> planos = sistema.listarPlanos(); // Buscar todos os planos cadastrados
                StringBuilder sb = new StringBuilder("Planos cadastrados:\n");
                for (Planos p : planos) {
                    sb.append("ID: ").append(p.getId()).append(" - ").append(p.getNome()).append("\n");
                }
                JOptionPane.showMessageDialog(funFrame, sb.toString());
            }
        });
        panel.add(listarPlanosButton);

        // Botão para atualizar plano
        JButton atualizarPlanoButton = new JButton("Atualizar Plano");
        atualizarPlanoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idPlano = Integer.parseInt(JOptionPane.showInputDialog("ID do Plano a Atualizar:"));
                String novoNome = JOptionPane.showInputDialog("Novo Nome do Plano:");
                double novoPreco = Double.parseDouble(JOptionPane.showInputDialog("Novo Preço:"));
                String novaFormaPagamento = JOptionPane.showInputDialog("Nova Forma de Pagamento:");
                sistema.atualizarPlano(idPlano, novoNome, novoPreco, novaFormaPagamento);
                JOptionPane.showMessageDialog(funFrame, "Plano atualizado com sucesso!");
            }
        });
        panel.add(atualizarPlanoButton);

        // Botão para remover plano
        JButton removerPlanoButton = new JButton("Remover Plano");
        removerPlanoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idPlano = Integer.parseInt(JOptionPane.showInputDialog("ID do Plano a Remover:"));
                sistema.removerPlano(idPlano);
                JOptionPane.showMessageDialog(funFrame, "Plano removido com sucesso!");
            }
        });
        panel.add(removerPlanoButton);

        // Botão de sair
        JButton sairButton = new JButton("Sair");
        sairButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Fecha o programa
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(sairButton);

        // Adicionar o painel à janela
        funFrame.add(panel);
        funFrame.setVisible(true);
    }
}
