package com.bigcorp.booking.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Serviette> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem (Serviette serviette){
        items.add(serviette);
    }

    public List<Serviette> getItems() {
        return items;
    }
}
