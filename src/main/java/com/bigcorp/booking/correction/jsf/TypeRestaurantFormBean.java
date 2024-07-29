package com.bigcorp.booking.correction.jsf;

/**
 * POJO pour que JSF stocke des données sur un type
 * de restaurant
 */
public class TypeRestaurantFormBean {

    private String id;

    private String nom;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
