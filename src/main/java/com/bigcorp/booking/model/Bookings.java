package com.bigcorp.booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @NotNull(message = "L'email est obligatoire.")
    @Email(message = "L'email doit être valide.")
    private String email;

    @Min(value = 1, message = "L'id doit être au moins un entier égale à 1.")
    private  Integer id_client;

    @NotNull(message = "Le nom est obligatoire.")
    @Size(min = 3, max = 30, message = "Le nom doit comporter entre 3 et 30 caractères.")
    private  String nom_client;

    @NotNull(message = "Le prenom est obligatoire.")
    @Size(min = 3, max = 30, message = "Le prenom doit comporter entre 3 et 30 caractères.")
    private  String prenom_client;

    public Bookings() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotNull(message = "L'email est obligatoire.") @Email(message = "L'email doit être valide.") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "L'email est obligatoire.") @Email(message = "L'email doit être valide.") String email) {
        this.email = email;
    }

    public @Min(value = 1, message = "L'id doit être au moins un entier égale à 1.") Integer getId_client() {
        return id_client;
    }

    public void setId_client(@Min(value = 1, message = "L'id doit être au moins un entier égale à 1.") Integer id_client) {
        this.id_client = id_client;
    }

    public @NotNull(message = "Le nom est obligatoire.") @Size(min = 3, max = 30, message = "Le nom doit comporter entre 3 et 30 caractères.") String getNom_client() {
        return nom_client;
    }

    public void setNom_client(@NotNull(message = "Le nom est obligatoire.") @Size(min = 3, max = 30, message = "Le nom doit comporter entre 3 et 30 caractères.") String nom_client) {
        this.nom_client = nom_client;
    }

    public @NotNull(message = "Le prenom est obligatoire.") @Size(min = 3, max = 30, message = "Le prenom doit comporter entre 3 et 30 caractères.") String getPrenom_client() {
        return prenom_client;
    }

    public void setPrenom_client(@NotNull(message = "Le prenom est obligatoire.") @Size(min = 3, max = 30, message = "Le prenom doit comporter entre 3 et 30 caractères.") String prenom_client) {
        this.prenom_client = prenom_client;
    }
}
