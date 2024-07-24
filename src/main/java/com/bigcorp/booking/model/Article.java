
package com.bigcorp.booking.model;

public class Article {
    private Long id;
    private String name;
    private int prix;

    // Constructeurs
    public Article() {}

    public Article(Long id, String name, int prix) {
        this.id = id;
        this.name = name;
        this.prix = prix;
    }

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

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}