package com.bigcorp.booking.dto;
import com.bigcorp.booking.model.Prix;

public class PrixDto {

    private String categorie;
    private int prixMin;
    private int prixMax;

    // Constructeur par défaut
    public PrixDto() {}

    // Constructeur basé sur l'énumération Prix
    public PrixDto(Prix prix) {
        this.categorie = prix.name();
        this.prixMin = prix.getPrixMin();
        this.prixMax = prix.getPrixMax();
    }

    // Getters et Setters

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(int prixMin) {
        this.prixMin = prixMin;
    }

    public int getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(int prixMax) {
        this.prixMax = prixMax;
    }
}