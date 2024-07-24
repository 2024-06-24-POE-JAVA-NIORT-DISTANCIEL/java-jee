package com.bigcorp.booking.model.tpfinal;

public class Utilisateur {
    private static int cpt = 0;
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Panier panier;

    public Utilisateur(String firstName, String lastName, String email, Panier panier) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.panier = panier;
        id = cpt++;
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

    public int getId() {
        return id;
    }

    public Panier getPanier() {
        return panier;
    }
}