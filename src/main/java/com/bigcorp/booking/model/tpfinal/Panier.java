package com.bigcorp.booking.model.tpfinal;

import java.util.TreeMap;

public class Panier {

    private static int cpt = 0;
    private int id;

    TreeMap<Integer, Article> contenu;

    public Panier(TreeMap<Integer, Article> contenu) {
        this.contenu = contenu;
        id = cpt++;
    }

    public void afficher() {
        for (Article article : contenu.values()) {
            System.out.println(article.getName() + " " + article.getDetail());
        }
    }

    public void ajouterAuPanier(Article article) {
        contenu.put(article.getID(), article);
    }

    public void retirerDuPanier(Article article) {
        contenu.remove(article.getID(), article);
    }

    public int getId() {
        return id;
    }
}