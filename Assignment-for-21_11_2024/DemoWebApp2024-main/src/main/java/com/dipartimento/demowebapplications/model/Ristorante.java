package com.dipartimento.demowebapplications.model;

import java.util.List;

public class Ristorante {

    private String nome;
    private String descrizione;
    private String ubicazione;
    private List<Piatto> piatti;

    public Ristorante(String nome, String descrizione, String ubicazione, List<Piatto> piatti) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.ubicazione = ubicazione;
        this.piatti = piatti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUbicazione() {
        return ubicazione;
    }

    public void setUbicazione(String ubicazione) {
        this.ubicazione = ubicazione;
    }

    public List<Piatto> getPiatti() {
        return piatti;
    }

    public void setPiatti(List<Piatto> piatti) {
        this.piatti = piatti;
    }
}