package com.dipartimento.demowebapplications.persistence.dao.impljdbc;

import com.dipartimento.demowebapplications.model.Piatto;
import com.dipartimento.demowebapplications.persistence.dao.PiattoDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PiattoDaoJDBC implements PiattoDao {

    private Connection connection;

    public PiattoDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Piatto> findAll() {
        List<Piatto> piatti = new ArrayList<>();
        String query = "SELECT * FROM piatto";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Piatto piatto = new Piatto(rs.getString("nome"), rs.getString("ingredienti"));
                piatti.add(piatto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return piatti;
    }

    @Override
    public Piatto findById(String nome) {
        String query = "SELECT nome, ingredienti FROM piatto WHERE nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nome);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new PiattoProxy(resultSet.getString("nome"), null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Piatto piatto) {
        if (findById(piatto.getNome()) == null) {
            create(piatto);
        } else {
            update(piatto);
        }
    }

    @Override
    public void create(Piatto piatto) {
        String query = "INSERT INTO piatto (nome, ingredienti) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, piatto.getNome());
            statement.setString(2, piatto.getIngredienti());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Piatto piatto) {
        String query = "UPDATE piatto SET ingredienti = ? WHERE nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, piatto.getIngredienti());
            statement.setString(2, piatto.getNome());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Piatto piatto) {
        String query = "DELETE FROM piatto WHERE nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, piatto.getNome());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Piatto> findAllByRistoranteName(String name) {
        List<Piatto> piatti = new ArrayList<>();
        String query = "SELECT * FROM piatto WHERE ristorante_nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Piatto piatto = new Piatto(rs.getString("nome"), rs.getString("ingredienti"));
                piatti.add(piatto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return piatti;
    }

    // Metodo findPiattiByRistorante
    public List<Piatto> findPiattiByRistorante(String nomeRistorante) {
        List<Piatto> piatti = new ArrayList<>();
        String query = "SELECT * FROM piatto WHERE ristorante_nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nomeRistorante);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Piatto piatto = new Piatto(rs.getString("nome"), rs.getString("ingredienti"));
                piatti.add(piatto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return piatti;
    }
}