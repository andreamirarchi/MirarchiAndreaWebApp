package com.dipartimento.demowebapplications.persistence.dao.impljdbc;

import com.dipartimento.demowebapplications.model.Piatto;
import com.dipartimento.demowebapplications.model.Ristorante;
import com.dipartimento.demowebapplications.persistence.dao.RistoranteDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RistoranteDaoJDBC implements RistoranteDao {

    private Connection connection;

    public RistoranteDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Ristorante> findAll() {
        List<Ristorante> ristoranti = new ArrayList<>();
        String query = "SELECT * FROM ristorante";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String nome = rs.getString("nome");
                String descrizione = rs.getString("descrizione");
                String ubicazione = rs.getString("ubicazione");
                List<Piatto> piatti = findAllPiattiByRistorante(nome);
                Ristorante ristorante = new Ristorante(nome, descrizione, ubicazione, piatti);
                ristoranti.add(ristorante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ristoranti;
    }

    @Override
    public Ristorante findById(String nome) {
        String query = "SELECT nome, descrizione, ubicazione FROM ristorante WHERE nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nome);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String descrizione = resultSet.getString("descrizione");
                String ubicazione = resultSet.getString("ubicazione");
                List<Piatto> piatti = findAllPiattiByRistorante(nome);
                return new Ristorante(nome, descrizione, ubicazione, piatti);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Ristorante ristorante) {
        if (findById(ristorante.getNome()) == null) {
            create(ristorante);
        } else {
            update(ristorante);
        }
    }

    @Override
    public void create(Ristorante ristorante) {
        String query = "INSERT INTO ristorante (nome, descrizione, ubicazione) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ristorante.getNome());
            statement.setString(2, ristorante.getDescrizione());
            statement.setString(3, ristorante.getUbicazione());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Ristorante ristorante) {
        String query = "UPDATE ristorante SET descrizione = ?, ubicazione = ? WHERE nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ristorante.getDescrizione());
            statement.setString(2, ristorante.getUbicazione());
            statement.setString(3, ristorante.getNome());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addPiattoToRistorante(Ristorante ristorante, Piatto piatto) {
        String query = "UPDATE piatto SET ristorante_nome = ? WHERE nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ristorante.getNome());  // Imposta il nome del ristorante
            statement.setString(2, piatto.getNome());      // Imposta il nome del piatto
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removePiattoFromRistorante(Ristorante ristorante, Piatto piatto) {
        String query = "UPDATE piatto SET ristorante_nome = NULL WHERE nome = ? AND ristorante_nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, piatto.getNome());      // Nome del piatto
            statement.setString(2, ristorante.getNome());  // Nome del ristorante
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(Ristorante ristorante) {
        String query = "DELETE FROM ristorante WHERE nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ristorante.getNome());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Piatto> findAllPiattiByRistorante(String nomeRistorante) {
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