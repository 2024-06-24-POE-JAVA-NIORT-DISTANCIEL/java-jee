package com.bigcorp.booking.classesStore;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
    private final List<Serviette> panier = new ArrayList<>();

    public List<Serviette> getPanier() {
        return panier;
    }

    public void addToPanier(Serviette article) {
        panier.add(article);
    }
}