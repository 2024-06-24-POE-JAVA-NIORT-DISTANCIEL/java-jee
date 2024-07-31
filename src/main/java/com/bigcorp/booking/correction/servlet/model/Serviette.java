package com.bigcorp.booking.correction.servlet.model;

/**
 * POJO représentant une serviette
 */
public class Serviette {

    private Integer id;
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
