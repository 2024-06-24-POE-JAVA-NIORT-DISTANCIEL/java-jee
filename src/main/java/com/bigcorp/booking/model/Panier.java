package com.bigcorp.booking.model;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    private List<Article> articles;

    public Panier() {
        this.articles = new ArrayList<>();
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void addArticle(Article article) {
        this.articles.add(article);
    }
}