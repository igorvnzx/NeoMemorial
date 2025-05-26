package ui;

import dao.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main {

    // Inicializando os DAOs
    private static ClienteDao clienteDAO = new ClienteDao();
    private static PagamentoDao pagamentoDAO = new PagamentoDao();
    private static PlanoDao planoDAO = new PlanoDao();
    private static CemitérioDao cemitérioDAO = new CemitérioDao();

    public static void main(String[] args) {
        boolean logado = mostrarTelaLogin();

        if (!logado) {
            JOptionPane.showMessageDialog(null, "Login cancelado ou inválido. Encerrando o sistema.");
            System.exit(0);
        }

        JFrame frame = new JFrame("Sistema Funerário");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JLabel titleLabel = new JLabel("Sistema Funerário - Gerenciamento");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel);

        JButton btnGestaoClientes = new JButton("Gestão de Clientes");
        JButton btnGestaoFinanceira = new JButton("Gestão Financeira");
        JButton btnPlanosEServicos = new JButton("Planos e Serviços");
        JButton btnGestaoCemiterios = new JButton("Gestão de Cemitérios");

        btnGestaoClientes.addActionListener(e -> mostrarGestaoClientes());
        btnGestaoFinanceira.addActionListener(e -> mostrarGestaoFinanceira());
        btnPlanosEServicos.addActionListener(e -> mostrarGestaoPlanos());
        btnGestaoCemiterios.addActionListener(e -> mostrarGestaoCemiterios());

        panel.add(btnGestaoClientes);
        panel.add(btnGestaoFinanceira);
        panel.add(btnPlanosEServicos);
        panel.add(btnGestaoCemiterios);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static boolean mostrarTelaLogin() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        panel.add(new JLabel("Usuário:"));
        panel.add(userField);
        panel.add(new JLabel("Senha:"));
        panel.add(passField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String usuario = userField.getText();
            String senha = new String(passField.getPassword());

            if (usuario.equals("admin") && senha.equals("1234")) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
                return mostrarTelaLogin();
            }
        } else {
            return false;
        }
    }

    private static void mostrarGestaoClientes() {
        JFrame cadastroFrame = new JFrame("Gestão de Clientes");
        cadastroFrame.setSize(600, 400);
        cadastroFrame.setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(6, 1));

        JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
        JButton btnListarClientes = new JButton("Listar Clientes");
        JButton btnExcluirCliente = new JButton("Excluir Cliente");

        btnCadastrarCliente.addActionListener(e -> mostrarTelaCadastroCliente());
        btnListarClientes.addActionListener(e -> listarClientes());
        btnExcluirCliente.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Informe o ID do cliente a ser excluído:");
            if (id != null) {
                clienteDAO.excluirCliente(Integer.parseInt(id));
                JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
            }
        });

        painel.add(btnCadastrarCliente);
        painel.add(btnListarClientes);
        painel.add(btnExcluirCliente);

        cadastroFrame.add(painel);
        cadastroFrame.setVisible(true);
    }

    private static void mostrarTelaCadastroCliente() {
        JFrame cadastroFrame = new JFrame("Cadastro de Cliente");
        cadastroFrame.setSize(400, 300);
        cadastroFrame.setLocationRelativeTo(null);

        JPanel cadastroPanel = new JPanel();
        cadastroPanel.setLayout(new GridLayout(5, 2));

        cadastroPanel.add(new JLabel("Nome:"));
        JTextField nomeField = new JTextField();
        cadastroPanel.add(nomeField);

        cadastroPanel.add(new JLabel("Endereço:"));
        JTextField enderecoField = new JTextField();
        cadastroPanel.add(enderecoField);

        cadastroPanel.add(new JLabel("Telefone:"));
        JTextField telefoneField = new JTextField();
        cadastroPanel.add(telefoneField);

        cadastroPanel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        cadastroPanel.add(emailField);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> {
            Cliente cliente = new Cliente();
            cliente.setNome(nomeField.getText());
            cliente.setEndereco(enderecoField.getText());
            cliente.setTelefone(telefoneField.getText());
            cliente.setEmail(emailField.getText());
            clienteDAO.adicionarCliente(cliente);
            JOptionPane.showMessageDialog(cadastroFrame, "Cliente Cadastrado!");
            cadastroFrame.dispose();
        });
        cadastroPanel.add(salvarButton);

        cadastroFrame.add(cadastroPanel);
        cadastroFrame.setVisible(true);
    }

    private static void listarClientes() {
        List<Cliente> clientes = clienteDAO.listarClientes();
        StringBuilder sb = new StringBuilder();
        for (Cliente cliente : clientes) {
            sb.append("ID: ").append(cliente.getId()).append("\n");
            sb.append("Nome: ").append(cliente.getNome()).append("\n");
            sb.append("Endereço: ").append(cliente.getEndereco()).append("\n");
            sb.append("Telefone: ").append(cliente.getTelefone()).append("\n");
            sb.append("Email: ").append(cliente.getEmail()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void mostrarGestaoFinanceira() {
        JFrame financeiraFrame = new JFrame("Gestão Financeira");
        financeiraFrame.setSize(600, 400);
        financeiraFrame.setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(6, 1));

        JButton btnCadastrarPagamento = new JButton("Cadastrar Pagamento");
        JButton btnListarPagamentos = new JButton("Listar Pagamentos");
        JButton btnExcluirPagamento = new JButton("Excluir Pagamento");

        btnCadastrarPagamento.addActionListener(e -> mostrarTelaCadastroPagamento());
        btnListarPagamentos.addActionListener(e -> listarPagamentos());
        btnExcluirPagamento.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Informe o ID do pagamento a ser excluído:");
            if (id != null) {
                pagamentoDAO.excluirPagamento(Integer.parseInt(id));
                JOptionPane.showMessageDialog(null, "Pagamento excluído com sucesso!");
            }
        });

        painel.add(btnCadastrarPagamento);
        painel.add(btnListarPagamentos);
        painel.add(btnExcluirPagamento);

        financeiraFrame.add(painel);
        financeiraFrame.setVisible(true);
    }

    private static void mostrarTelaCadastroPagamento() {
        JFrame pagamentoFrame = new JFrame("Cadastro de Pagamento");
        pagamentoFrame.setSize(400, 300);
        pagamentoFrame.setLocationRelativeTo(null);

        JPanel pagamentoPanel = new JPanel();
        pagamentoPanel.setLayout(new GridLayout(5, 2));

        pagamentoPanel.add(new JLabel("Valor:"));
        JTextField valorField = new JTextField();
        pagamentoPanel.add(valorField);

        pagamentoPanel.add(new JLabel("ID do Cliente:"));
        JTextField idClienteField = new JTextField();
        pagamentoPanel.add(idClienteField);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> {
            Pagamento pagamento = new Pagamento();
            pagamento.setValor(Double.parseDouble(valorField.getText()));
            pagamento.setIdCliente(Integer.parseInt(idClienteField.getText()));
            pagamento.setStatusPagamento("Pendente");
            pagamento.setMetodoPagamento("Pix");
            pagamentoDAO.adicionarPagamento(pagamento);
            JOptionPane.showMessageDialog(pagamentoFrame, "Pagamento Cadastrado!");
            pagamentoFrame.dispose();
        });
        pagamentoPanel.add(salvarButton);

        pagamentoFrame.add(pagamentoPanel);
        pagamentoFrame.setVisible(true);
    }

    private static void listarPagamentos() {
        List<Pagamento> pagamentos = pagamentoDAO.listarPagamentos();
        StringBuilder sb = new StringBuilder();
        for (Pagamento pagamento : pagamentos) {
            sb.append("ID: ").append(pagamento.getId()).append("\n");
            sb.append("ID Cliente: ").append(pagamento.getIdCliente()).append("\n");
            sb.append("Valor: ").append(pagamento.getValor()).append("\n");
            sb.append("Status: ").append(pagamento.getStatusPagamento()).append("\n");
            sb.append("Método: ").append(pagamento.getMetodoPagamento()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void mostrarGestaoPlanos() {
        JFrame planosFrame = new JFrame("Gestão de Planos e Serviços");
        planosFrame.setSize(600, 400);
        planosFrame.setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(6, 1));

        JButton btnCadastrarPlano = new JButton("Cadastrar Plano");
        JButton btnListarPlanos = new JButton("Listar Planos");
        JButton btnExcluirPlano = new JButton("Excluir Plano");

        btnCadastrarPlano.addActionListener(e -> mostrarTelaCadastroPlano());
        btnListarPlanos.addActionListener(e -> listarPlanos());
        btnExcluirPlano.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Informe o ID do plano a ser excluído:");
            if (id != null) {
                planoDAO.excluirPlano(Integer.parseInt(id));
                JOptionPane.showMessageDialog(null, "Plano excluído com sucesso!");
            }
        });

        painel.add(btnCadastrarPlano);
        painel.add(btnListarPlanos);
        painel.add(btnExcluirPlano);

        planosFrame.add(painel);
        planosFrame.setVisible(true);
    }

    private static void mostrarTelaCadastroPlano() {
        JFrame planoFrame = new JFrame("Cadastro de Plano");
        planoFrame.setSize(400, 300);
        planoFrame.setLocationRelativeTo(null);

        JPanel planoPanel = new JPanel();
        planoPanel.setLayout(new GridLayout(5, 2));

        planoPanel.add(new JLabel("Nome:"));
        JTextField nomeField = new JTextField();
        planoPanel.add(nomeField);

        planoPanel.add(new JLabel("Descrição:"));
        JTextField descricaoField = new JTextField();
        planoPanel.add(descricaoField);

        planoPanel.add(new JLabel("Preço:"));
        JTextField precoField = new JTextField();
        planoPanel.add(precoField);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> {
            Plano plano = new Plano();
            plano.setNome(nomeField.getText());
            plano.setDescricao(descricaoField.getText());
            plano.setPreco(Double.parseDouble(precoField.getText()));
            planoDAO.adicionarPlano(plano);
            JOptionPane.showMessageDialog(planoFrame, "Plano Cadastrado!");
            planoFrame.dispose();
        });
        planoPanel.add(salvarButton);

        planoFrame.add(planoPanel);
        planoFrame.setVisible(true);
    }

    private static void listarPlanos() {
        List<Plano> planos = planoDAO.listarPlanos();
        StringBuilder sb = new StringBuilder();
        for (Plano plano : planos) {
            sb.append("ID: ").append(plano.getId()).append("\n");
            sb.append("Nome: ").append(plano.getNome()).append("\n");
            sb.append("Descrição: ").append(plano.getDescricao()).append("\n");
            sb.append("Preço: ").append(plano.getPreco()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void mostrarGestaoCemiterios() {
        JFrame cemiteriosFrame = new JFrame("Gestão de Cemitérios");
        cemiteriosFrame.setSize(600, 400);
        cemiteriosFrame.setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(6, 1));

        JButton btnCadastrarCemitério = new JButton("Cadastrar Cemitério");
        JButton btnListarCemitérios = new JButton("Listar Cemitérios");
        JButton btnExcluirCemiterio = new JButton("Excluir Cemitério");

        btnCadastrarCemitério.addActionListener(e -> mostrarTelaCadastroCemiterio());
        btnListarCemitérios.addActionListener(e -> listarCemiterios());
        btnExcluirCemiterio.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Informe o ID do cemitério a ser excluído:");
            if (id != null) {
                cemitérioDAO.excluirCemitério(Integer.parseInt(id));
                JOptionPane.showMessageDialog(null, "Cemitério excluído com sucesso!");
            }
        });

        painel.add(btnCadastrarCemitério);
        painel.add(btnListarCemitérios);
        painel.add(btnExcluirCemiterio);

        cemiteriosFrame.add(painel);
        cemiteriosFrame.setVisible(true);
    }

    private static void mostrarTelaCadastroCemiterio() {
        JFrame cemiterioFrame = new JFrame("Cadastro de Cemitério");
        cemiterioFrame.setSize(400, 300);
        cemiterioFrame.setLocationRelativeTo(null);

        JPanel cemiterioPanel = new JPanel();
        cemiterioPanel.setLayout(new GridLayout(5, 2));

        cemiterioPanel.add(new JLabel("Nome:"));
        JTextField nomeField = new JTextField();
        cemiterioPanel.add(nomeField);

        cemiterioPanel.add(new JLabel("Endereço:"));
        JTextField enderecoField = new JTextField();
        cemiterioPanel.add(enderecoField);

        cemiterioPanel.add(new JLabel("Capacidade:"));
        JTextField capacidadeField = new JTextField();
        cemiterioPanel.add(capacidadeField);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> {
            Cemitério cemitério = new Cemitério();
            cemitério.setNome(nomeField.getText());
            cemitério.setEndereco(enderecoField.getText());
            cemitério.setCapacidade(Integer.parseInt(capacidadeField.getText()));
            cemitérioDAO.adicionarCemitério(cemitério);
            JOptionPane.showMessageDialog(cemiterioFrame, "Cemitério Cadastrado!");
            cemiterioFrame.dispose();
        });
        cemiterioPanel.add(salvarButton);

        cemiterioFrame.add(cemiterioPanel);
        cemiterioFrame.setVisible(true);
    }

    private static void listarCemiterios() {
        List<Cemitério> cemiterios = cemitérioDAO.listarCemitérios();
        StringBuilder sb = new StringBuilder();
        for (Cemitério cemitério : cemiterios) {
            sb.append("ID: ").append(cemitério.getId()).append("\n");
            sb.append("Nome: ").append(cemitério.getNome()).append("\n");
            sb.append("Endereço: ").append(cemitério.getEndereco()).append("\n");
            sb.append("Capacidade: ").append(cemitério.getCapacidade()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
