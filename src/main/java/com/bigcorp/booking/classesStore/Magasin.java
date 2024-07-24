package com.bigcorp.booking.classesStore;

import java.util.ArrayList;
import java.util.List;

public class Magasin {
    private final List<Serviette> listeArticles = new ArrayList<>(); 

    public void addArticle(Serviette serviette) {
        listeArticles.add(serviette);
    }

    public List<Serviette> getArticles() {
        return listeArticles;
    }
}