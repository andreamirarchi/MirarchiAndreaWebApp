package com.dipartimento.demowebapplications.persistence.dao;

import com.dipartimento.demowebapplications.model.Piatto;
import com.dipartimento.demowebapplications.model.Ristorante;

import java.util.List;

public interface RistoranteDao {

    List<Ristorante> findAll();

    Ristorante findById(String nome);

    void save(Ristorante ristorante);

    void delete(Ristorante ristorante);

    void create(Ristorante ristorante);

    void update(Ristorante ristorante);

    void addPiattoToRistorante(Ristorante ristorante, Piatto piatto);

    void removePiattoFromRistorante(Ristorante ristorante, Piatto piatto);
}