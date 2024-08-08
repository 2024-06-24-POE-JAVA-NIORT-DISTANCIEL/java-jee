package com.bigcorp.booking.dto;

import jakarta.validation.constraints.*;
//import lombok.Data;

public class ClientDto {

    @Null(message = "L'id doit être nul lors de la création d'un client.")
    private Long id;

    @NotNull(message = "Le nom est obligatoire.")
    @Size(min = 1, max = 100, message = "Le nom doit comporter entre 1 et 100 caractères.")
    private String name;

    @NotNull(message = "L'email est obligatoire.")
    @Email(message = "L'email doit être valide.")
    private String email;

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Le numéro de téléphone doit être valide.")
    private String phone;

    @NotNull(message = "L'adresse est obligatoire.")
    @Size(min = 1, max = 255, message = "L'adresse doit comporter entre 1 et 255 caractères.")
    private String address;

    @Min(value = 18, message = "L'âge doit être au moins 18 ans.")
    @Max(value = 120, message = "L'âge doit être au plus 120 ans.")
    private int age;

    private boolean premium;

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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