package com.bigcorp.booking.model;

public class Serviette {
    private int id;
    private String name = "Serviette";
    private String description = "la plus belle des serviette";
    private double price = 15.99;

    public Serviette(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
