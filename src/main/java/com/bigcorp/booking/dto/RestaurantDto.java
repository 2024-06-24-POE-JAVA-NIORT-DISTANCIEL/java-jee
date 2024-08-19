package com.bigcorp.booking.dto;
import com.bigcorp.booking.dto.PrixDto;
import com.bigcorp.booking.dto.RestaurantTypeDto;


import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class RestaurantDto {

    @Null(message = "L'id doit être nul lors de la création d'un restaurant.")
    private Integer id;

    @NotNull(message = "Le nom est obligatoire.")
    @Size(min = 3, max = 30, message = "Le nom doit comporter entre 3 et 30 caractères.")
    private String nom;

    @NotNull(message = "L'adresse est obligatoire.")
    @Size(min = 3, max = 50, message = "L'adresse doit comporter entre 3 et 50 caractères.")
    private String adresse;

    @NotNull(message = "Le prix est obligatoire.")
    @Min(value = 1, message = "Le prix doit être au minimum de 1 euro.")
    private PrixDto prixDto;

    @NotNull(message = "L'adresse du patron est obligatoire.")
    @Size(min = 3, max = 50, message = "L'adresse du patron doit comporter entre 3 et 50 caractères.")
    private String adresseDuPatron;

    private LocalDateTime ouverture;

    private Boolean actif;

    private RestaurantTypeDto restaurantType;

    // Getters and Setters

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

    public PrixDto getPrix() {
        return prixDto;
    }

    public void setPrix(PrixDto prixDto) {
        this.prixDto = prixDto;
    }

    public String getAdresseDuPatron() {
        return adresseDuPatron;
    }

    public void setAdresseDuPatron(String adresseDuPatron) {
        this.adresseDuPatron = adresseDuPatron;
    }

    public LocalDateTime getOuverture() {
        return ouverture;
    }

    public void setOuverture(LocalDateTime ouverture) {
        this.ouverture = ouverture;
    }

    public RestaurantTypeDto getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(RestaurantTypeDto restaurantType) {
        this.restaurantType = restaurantType;
    }
}
