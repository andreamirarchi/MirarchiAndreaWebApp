package com.dipartimento.demowebapplications.model;

import java.util.List;

public class Piatto {

    private String nome;
    private String ingredienti;
    private List<Ristorante> ristoranti;

    public Piatto(String nome, String ingredienti) {
        this.nome = nome;
        this.ingredienti = ingredienti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }
}