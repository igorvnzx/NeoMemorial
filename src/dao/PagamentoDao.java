package dao;

import model.Pagamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDao {
    private Connection connection;

    public PagamentoDao() {
        try {
            // Conectar ao banco de dados
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_funeral", "root", "251509Ig.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Adiciona um novo pagamento
    public void adicionarPagamento(Pagamento pagamento) {
        String sql = "INSERT INTO pagamentos (idCliente, valor, dataVencimento, statusPagamento, metodoPagamento) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pagamento.getIdCliente());
            stmt.setDouble(2, pagamento.getValor());

            if (pagamento.getDataVencimento() != null) {
                stmt.setDate(3, new java.sql.Date(pagamento.getDataVencimento().getTime()));
            } else {
                stmt.setDate(3, null); // ou lance uma exceção se a data for obrigatória
            }

            stmt.setString(4, pagamento.getStatusPagamento());
            stmt.setString(5, pagamento.getMetodoPagamento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lista todos os pagamentos
    public List<Pagamento> listarPagamentos() {
        List<Pagamento> pagamentos = new ArrayList<>();
        String sql = "SELECT * FROM pagamentos";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Pagamento pagamento = new Pagamento();
                pagamento.setId(rs.getInt("id"));
                pagamento.setIdCliente(rs.getInt("idCliente"));
                pagamento.setValor(rs.getDouble("valor"));
                pagamento.setDataVencimento(rs.getDate("dataVencimento"));
                pagamento.setStatusPagamento(rs.getString("statusPagamento"));
                pagamento.setMetodoPagamento(rs.getString("metodoPagamento"));
                pagamentos.add(pagamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pagamentos;
    }

    // Atualiza as informações de um pagamento
    public void atualizarPagamento(Pagamento pagamento) {
        String sql = "UPDATE pagamentos SET valor = ?, dataVencimento = ?, statusPagamento = ?, metodoPagamento = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, pagamento.getValor());
            stmt.setDate(2, new java.sql.Date(pagamento.getDataVencimento().getTime()));
            stmt.setString(3, pagamento.getStatusPagamento());
            stmt.setString(4, pagamento.getMetodoPagamento());
            stmt.setInt(5, pagamento.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Exclui um pagamento
    public void excluirPagamento(int id) {
        String sql = "DELETE FROM pagamentos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
