package com.bigcorp.booking.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Towel> towels;

    public ShoppingCart() {
        this.towels = new ArrayList<>();
    }

    public List<Towel> getTowels() {
        return towels;
    }
}

