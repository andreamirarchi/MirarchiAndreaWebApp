package com.dipartimento.demowebapplications.persistence.dao.impljdbc;

import com.dipartimento.demowebapplications.model.Piatto;
import com.dipartimento.demowebapplications.model.Ristorante;
import com.dipartimento.demowebapplications.persistence.DBManager;

import java.util.List;

public class RistoranteProxy extends Ristorante {

    public RistoranteProxy(String nome, String descrizione, String ubicazione) {
        super(nome, descrizione, ubicazione, null);
    }

    @Override
    public List<Piatto> getPiatti() {
        if (this.piatti == null) {
            this.piatti = DBManager.getInstance().getPiattoDao().findPiattiByRistorante(this.getNome());
        }
        return this.piatti;
    }
}