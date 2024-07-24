package com.bigcorp.booking.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ServietteType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;
    private int quantite;
    private String reference;
    private int prix;
    private String description;

    public ServietteType(int id, String name, int quantite, String reference, int prix, String description) {
        this.id = id;
        this.name = name;
        this.quantite = quantite;
        this.reference = reference;
        this.prix = prix;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getQuantite() {
        return quantite;
    }
    public String getReference() {
        return reference;
    }
    public int getPrix() {
        return prix;
    }
    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public void setPrix(int prix) {
        this.prix = prix;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
