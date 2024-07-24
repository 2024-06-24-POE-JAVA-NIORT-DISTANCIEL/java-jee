package com.bigcorp.booking.model;

public class User {
    private String username;
    private ShoppingCart cart;

    public User(String username) {
        this.username = username;
        this.cart = new ShoppingCart();
    }

    public String getUsername() {
        return username;
    }

    public ShoppingCart getCart() {
        return cart;
    }
}