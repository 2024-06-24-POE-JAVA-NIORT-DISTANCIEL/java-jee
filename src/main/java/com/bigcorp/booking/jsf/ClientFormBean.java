package com.bigcorp.booking.jsf;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public class ClientFormBean {

    @Min(0)
    private Integer id; // Changed to Integer to handle null value for new clients

    @NotNull(message = "Le nom est obligatoire")
    @Size(min = 3, max = 30, message = "Le nom doit comporter entre 3 et 30 caractères")
    private String name;

    @NotNull(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;

    @NotNull(message = "Le téléphone est obligatoire")
    @Pattern(regexp = "\\d{10}", message = "Le téléphone doit comporter 10 chiffres")
    private String phone;

    @NotNull(message = "L'adresse est obligatoire")
    @Size(min = 1, max = 100, message = "L'adresse doit comporter entre 1 et 100 caractères")
    private String address;

    @NotNull(message = "L'âge est obligatoire")
    @Min(value = 1, message = "L'âge doit être au moins 1")
    @Max(value = 120, message = "L'âge doit être au plus 120")
    private int age;

    private boolean premium;

    // Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
