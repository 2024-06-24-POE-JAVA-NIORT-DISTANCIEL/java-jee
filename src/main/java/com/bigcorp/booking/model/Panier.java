package com.bigcorp.booking.model;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    private List<ServietteType> articles;

    public Panier() {
        this.articles = new ArrayList<>();
    }

    public List<ServietteType> getArticles() {
        return articles;
    }

    public void addArticle(ServietteType article) {
        this.articles.add(article);
    }
}