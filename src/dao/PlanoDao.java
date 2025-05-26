package dao;

import model.Plano;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanoDao {
    private Connection connection;

    public PlanoDao() {
        try {
            // Conectar ao banco de dados
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_funeral", "root", "251509Ig.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Adiciona um novo plano
    public void adicionarPlano(Plano plano) {
        String sql = "INSERT INTO planos (nome, descricao, preco) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, plano.getNome());
            stmt.setString(2, plano.getDescricao());
            stmt.setDouble(3, plano.getPreco());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lista todos os planos
    public List<Plano> listarPlanos() {
        List<Plano> planos = new ArrayList<>();
        String sql = "SELECT * FROM planos";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Plano plano = new Plano();
                plano.setId(rs.getInt("id"));
                plano.setNome(rs.getString("nome"));
                plano.setDescricao(rs.getString("descricao"));
                plano.setPreco(rs.getDouble("preco"));
                planos.add(plano);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planos;
    }

    // Atualiza as informações de um plano
    public void atualizarPlano(Plano plano) {
        String sql = "UPDATE planos SET nome = ?, descricao = ?, preco = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, plano.getNome());
            stmt.setString(2, plano.getDescricao());
            stmt.setDouble(3, plano.getPreco());
            stmt.setInt(4, plano.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Exclui um plano
    public void excluirPlano(int id) {
        String sql = "DELETE FROM planos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
