package com.bigcorp.booking.model;

public class Personnage {

    private String nom;
    private String prenom;

    public Personnage(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return String.format("Le nom : %s\nLe prénom %s", nom, prenom);
    }
}
