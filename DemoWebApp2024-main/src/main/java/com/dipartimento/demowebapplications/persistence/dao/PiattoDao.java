package com.dipartimento.demowebapplications.persistence.dao;

import com.dipartimento.demowebapplications.model.Piatto;

import java.util.List;

public interface PiattoDao {

    public List<Piatto> findAll();

    public Piatto findById(String nome);

    public void save(Piatto piatto);

    public void delete(Piatto piatto);

    public List<Piatto> findAllByRistoranteName(String name);

    public void create(Piatto piatto);

    public void update(Piatto piatto);

    List<Piatto> findPiattiByRistorante(String nomeRistorante);
}