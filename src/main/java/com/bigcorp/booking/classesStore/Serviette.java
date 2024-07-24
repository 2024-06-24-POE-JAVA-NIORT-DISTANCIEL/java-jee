package com.bigcorp.booking.classesStore;

public class Serviette {
    private Integer id = 0;
    private String nom;
    private static Integer compteur = 0;

    public Serviette(String nom) {
        this.nom = nom;
        updateCompteur();
    }

    private void updateCompteur() {
        if (compteur >= 3) {
            compteur = 0;
        }

        compteur++;
        id = compteur;
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}