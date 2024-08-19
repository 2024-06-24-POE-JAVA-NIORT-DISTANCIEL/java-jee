package com.bigcorp.booking.service;


import com.bigcorp.booking.dao.ReservationDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

/**
 * Service pour la gestion des bookings.
 */
@Stateless
public class ReservationService {

    @Inject
    private ReservationDao reservationDao;



    /**
     * Méthode utilitaire pour afficher les logs avec le statut.
     * @param methodName le nom de la méthode
     * @param status le statut
     * @param args les arguments
     */
    private void logCall(String methodName, String status, Object... args) {
        System.out.println("Méthode appelée : " + methodName);
        System.out.println("Statut : " + status);
        for (Object arg : args) {
            System.out.println("Argument : " + arg);
        }
    }
}
