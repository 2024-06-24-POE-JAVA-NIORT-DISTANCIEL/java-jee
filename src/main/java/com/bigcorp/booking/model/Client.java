package com.bigcorp.booking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le nom est obligatoire.")
    @Size(min = 3, max = 30, message = "Le nom doit comporter entre 3 et 30 caractères.")
    private String nom;

    @NotNull(message = "Le prenom est obligatoire.")
    @Size(min = 3, max = 30, message = "Le prenom doit comporter entre 3 et 30 caractères.")
    private String prenom;

    @NotNull(message = "L'email est obligatoire.")
    @Email(message = "L'email doit être valide.")
    private String email;

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Le numéro de téléphone doit être valide.")
    private String phone;

    @NotNull(message = "L'adresse est obligatoire.")
    @Size(min = 3, max = 50, message = "L'adresse doit comporter entre 3 et 50 caractères.")
    private String address;

    @Min(value = 18, message = "L'âge doit être au moins 18 ans.")
    @Max(value = 120, message = "L'âge doit être au plus 120 ans.")
    private int age;

    private boolean premium;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<Reservation> reservations = new HashSet<>();

    // Getters and Setters

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }



}
