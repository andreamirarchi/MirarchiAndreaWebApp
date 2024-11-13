package com.dipartimento.demowebapplications.persistence.dao.impljdbc;

import com.dipartimento.demowebapplications.model.Piatto;
import com.dipartimento.demowebapplications.persistence.DBManager;

public class PiattoProxy extends Piatto {

    public PiattoProxy(String nome, String ingredienti) {
        super(nome, ingredienti);
    }

    @Override
    public String getIngredienti() {
        if (getIngredienti() == null) {
            Piatto piatto = DBManager.getInstance().getPiattoDao().findByPrimaryKey(this.getNome());
            if (piatto != null) {
                this.setIngredienti(piatto.getIngredienti());
            }
        }
        return super.getIngredienti();
    }
}