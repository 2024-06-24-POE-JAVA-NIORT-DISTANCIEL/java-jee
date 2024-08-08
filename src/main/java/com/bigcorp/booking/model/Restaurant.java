package com.bigcorp.booking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

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
     * Ceci est une relation entre REstaurant et RestaurantType
     * Elle est persistée en base de données par la colonne RESTAURANT_TYPE_ID de
     * la table RESTAURANT. En indiquant fetchType=LAZY, on indique
     * qu'on ne veut pas systématiquement récupérer de la base un
     * RestaurantType, quand on récupère un Restaurant
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="RESTAURANT_TYPE_ID")
    private RestaurantType restaurantType;

    /**
     * Les autres attributs sont automatiquement persistés dans une colonne
     * qui porte le même nom que l'attribut
     */
    @NotNull(message = "Le nom est obligatoire.")
    @Size(min = 3, max = 30, message = "Le nom doit comporter entre 3 et 30 caractères.")
    private String nom;

    @NotNull(message = "L'adresse est obligatoire.")
    @Size(min = 3, max = 50, message = "L'adresse doit comporter entre 3 et 50 caractères.")
    private String adresse;

    @Enumerated(EnumType.STRING)
    @Min(value = 1, message = "Le prix doit être au minimum de 1 euros.")
    private Prix prix;

    private LocalDateTime ouverture;

    /**
     * Ici, on surcharge le comportement par défaut en indiquant
     * explicitement un nom de colonne différent de celui de
     * l'attribut
     */
    @Column(name = "ADRESSE_SPECIALE")
    @NotNull(message = "L'adresse est obligatoire.")
    @Size(min = 3, max = 50, message = "L'adresse doit comporter entre 3 et 50 caractères.")
    private String adresseDuPatron;

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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Prix getPrix() {
        return prix;
    }

    public void setPrix(Prix prix) {
        this.prix = prix;
    }

    public String getAdresseDuPatron() {
        return adresseDuPatron;
    }

    public void setAdresseDuPatron(String adresseDuPatron) {
        this.adresseDuPatron = adresseDuPatron;
    }

    public RestaurantType getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(RestaurantType restaurantType) {
        this.restaurantType = restaurantType;
    }

    public LocalDateTime getOuverture() {
        return ouverture;
    }

    public void setOuverture(LocalDateTime ouverture) {
        this.ouverture = ouverture;
    }
}