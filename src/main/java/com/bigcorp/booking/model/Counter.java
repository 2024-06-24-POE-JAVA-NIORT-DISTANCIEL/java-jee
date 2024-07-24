package com.bigcorp.booking.model;

public class Counter {

    private Double compteur;
    private Double aleatoire;

    public Counter(Double compteur, Double aleatoire) {
        this.compteur = compteur;
        this.aleatoire = Math.random()*1;
    }

    public Double getCompteur() {
        return compteur;
    }

    public synchronized Double incrementer(){
        compteur +=1;
        return compteur;
    }

    public void setCompteur(Double compteur) {
        this.compteur = compteur;
    }

    public Double getAleatoire() {
        return aleatoire;
    }

    public void setAleatoire(Double aleatoire) {
        this.aleatoire = aleatoire;
    }
}