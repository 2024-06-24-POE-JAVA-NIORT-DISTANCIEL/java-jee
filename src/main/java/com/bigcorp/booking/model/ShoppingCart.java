package com.bigcorp.booking.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Towel> towels;

    public ShoppingCart() {
        this.towels = new ArrayList<>();
    }

    public void addTowel(Towel towel) {
        towels.add(towel);
    }

    public void removeTowel(Towel towel) {
        towels.remove(towel);
    }

    public List<Towel> getTowels() {
        return towels;
    }

    public void setTowels(List<Towel> towels) {
        this.towels = towels;
    }
}