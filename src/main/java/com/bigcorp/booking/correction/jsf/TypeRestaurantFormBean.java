package com.bigcorp.booking.correction.jsf;

/**
 * POJO pour que JSF stocke des données sur un type
 * de restaurant
 */
public class TypeRestaurantFormBean {

    private Integer id;

    private String nom;

    private Boolean actif;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }
}
