package com.bigcorp.booking.model;

public class Voiture {

    private Long id;
    private String numeroImmatriculation;
    private boolean actif;
    private String dateImmatriculation;

    // Constructeur par défaut
    public Voiture() {}

    // Constructeur avec paramètres
    public Voiture(Long id, String numeroImmatriculation, boolean actif, String dateImmatriculation) {
        this.id = id;
        this.numeroImmatriculation = numeroImmatriculation;
        this.actif = actif;
        this.dateImmatriculation = dateImmatriculation;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroImmatriculation() {
        return numeroImmatriculation;
    }

    public void setNumeroImmatriculation(String numeroImmatriculation) {
        this.numeroImmatriculation = numeroImmatriculation;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getDateImmatriculation() {
        return dateImmatriculation;
    }

    public void setDateImmatriculation(String dateImmatriculation) {
        this.dateImmatriculation = dateImmatriculation;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "id=" + id +
                ", numeroImmatriculation='" + numeroImmatriculation + '\'' +
                ", actif=" + actif +
                ", dateImmatriculation='" + dateImmatriculation + '\'' +
                '}';
    }
}
