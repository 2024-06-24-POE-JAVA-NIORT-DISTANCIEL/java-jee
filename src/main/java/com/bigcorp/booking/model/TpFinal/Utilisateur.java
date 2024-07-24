package com.bigcorp.booking.model.TpFinal;

public class Utilisateur {

    private static int id = 0;
    private String firstName;
    private String lastName;
    private String email;
    private Panier panier;

    public Utilisateur(String firstName, String lastName, String email, Panier panier) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.panier = panier;
        id++;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static int getId() {
        return id;
    }

    public Panier getPanier() {
        return panier;
    }
}