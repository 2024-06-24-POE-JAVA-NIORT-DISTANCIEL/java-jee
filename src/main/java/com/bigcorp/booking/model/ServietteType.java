package com.bigcorp.booking.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ServietteType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int quantite;
    private String reference;

    public ServietteType(Long id, String name, int quantite, String reference) {
        this.id = id;
        this.name = name;
        this.quantite = quantite;
        this.reference = reference;
    }

    public Long getId() {
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

    public void setId(Long id) {
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
}
