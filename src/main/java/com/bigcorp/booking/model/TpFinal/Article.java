package com.bigcorp.booking.model.TpFinal;

public class Article {


    private static int ID = 1;
    private String name;
    private String detail;

    public Article(String name, String detail) {
        this.name = name;
        this.detail = detail;
        ID++;
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