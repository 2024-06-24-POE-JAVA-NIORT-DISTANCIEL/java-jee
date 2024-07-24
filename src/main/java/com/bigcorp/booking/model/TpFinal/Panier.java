package com.bigcorp.booking.model.TpFinal;

import java.util.TreeMap;

public class Panier {

    private static int id = 0;
    TreeMap<Integer, Article> contenu;

    public Panier(TreeMap<Integer, Article> contenu) {
        this.contenu = contenu;
        id++;
    }

    public void afficher() {
        for (Article article : contenu.values()) {
            System.out.println(article.getName() + " " + article.getDetail());
        }
    }

    public void ajouterAuPanier(Article article) {
        contenu.put(Article.getID(), article);
    }

    public void retirerDuPanier(Article article) {
        contenu.remove(Article.getID(), article);
    }
}