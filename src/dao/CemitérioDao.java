package dao;

import model.Cemitério;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CemitérioDao {
    private Connection connection;

    public CemitérioDao() {
        try {
            // Conectar ao banco de dados
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_funeral", "root", "251509Ig.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Adiciona um novo cemitério
    public void adicionarCemitério(Cemitério cemitério) {
        String sql = "INSERT INTO cemitérios (nome, endereco, capacidade) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cemitério.getNome());
            stmt.setString(2, cemitério.getEndereco());
            stmt.setInt(3, cemitério.getCapacidade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lista todos os cemitérios
    public List<Cemitério> listarCemitérios() {
        List<Cemitério> cemitérios = new ArrayList<>();
        String sql = "SELECT * FROM cemitérios";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Cemitério cemitério = new Cemitério();
                cemitério.setId(rs.getInt("id"));
                cemitério.setNome(rs.getString("nome"));
                cemitério.setEndereco(rs.getString("endereco"));
                cemitério.setCapacidade(rs.getInt("capacidade"));
                cemitérios.add(cemitério);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cemitérios;
    }

    // Atualiza as informações de um cemitério
    public void atualizarCemitério(Cemitério cemitério) {
        String sql = "UPDATE cemitérios SET nome = ?, endereco = ?, capacidade = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cemitério.getNome());
            stmt.setString(2, cemitério.getEndereco());
            stmt.setInt(3, cemitério.getCapacidade());
            stmt.setInt(4, cemitério.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Exclui um cemitério
    public void excluirCemitério(int id) {
        String sql = "DELETE FROM cemitérios WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
