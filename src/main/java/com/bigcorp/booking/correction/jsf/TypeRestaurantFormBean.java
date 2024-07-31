package com.bigcorp.booking.correction.jsf;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * POJO pour que JSF stocke des données sur un type
 * de restaurant
 */
public class TypeRestaurantFormBean {

    /**
     * Annotation de validation empêchant l'id d'être négatif
     */
    @Min(0)
    private Integer id;

    /**
     * Le nom doit être non nul ET avoir une taille entre 3 et 40 caractères (compris)
     */
    @NotNull
    @Size(min=3, max=40)
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
