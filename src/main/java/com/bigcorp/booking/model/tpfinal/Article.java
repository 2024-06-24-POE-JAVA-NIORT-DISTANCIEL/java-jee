package com.bigcorp.booking.model.tpfinal;

public class Article {

    private static int compteur = 0;
    private static int ID;
    private String name;
    private String detail;

    public Article(String name, String detail) {
        this.name = name;
        this.detail = detail;
        this.ID = ++compteur;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public static int getID() {
        return ID;
    }
}