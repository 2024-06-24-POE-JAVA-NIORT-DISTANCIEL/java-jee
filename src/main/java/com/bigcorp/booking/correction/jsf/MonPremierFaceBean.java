package com.bigcorp.booking.correction.jsf;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.time.LocalDateTime;

/**
 * Ceci injecte dans JSF une instance de MonPremierFaceBean
 * qui s'appelle (par défaut) monPremierFaceBean
 */
@Named
@RequestScoped
public class MonPremierFaceBean {

    private String message = "Un super message qui vient du code Java";

    private LocalDateTime dateCourante = LocalDateTime.now();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateCourante() {
        return dateCourante;
    }

    public void setDateCourante(LocalDateTime dateCourante) {
        this.dateCourante = dateCourante;
    }
}
