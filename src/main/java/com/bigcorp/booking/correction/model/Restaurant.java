package com.bigcorp.booking.correction.model;

import jakarta.persistence.*;

/**
 * Entité : classe annotée avec @Entity
 * de façon à ce que JPA puisse la persister
 */
@Entity
public class Restaurant {

    /**
     * Cet attribut est annoté avec @Id : il correspond
     * à la colonne qui contient la clé primaire.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * Les autres attributs sont automatiquement persistés dans une colonne
     * qui porte le même nom que l'attribut
     */
    private String nom;


    private String adresse;

    /**
     * Ici, on surcharge le comportement par défaut en indiquant
     * explicitement un nom de colonne différent de celui de
     * l'attribut
     */
    @Column(name = "ADRESSE_SPECIALE")
    private String adresseDuPatron;

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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresseDuPatron() {
        return adresseDuPatron;
    }

    public void setAdresseDuPatron(String adresseDuPatron) {
        this.adresseDuPatron = adresseDuPatron;
    }
}
