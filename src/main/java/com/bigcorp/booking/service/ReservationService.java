package com.bigcorp.booking.service;


import com.bigcorp.booking.dao.ClientDao;
import com.bigcorp.booking.dao.ReservationDao;
import com.bigcorp.booking.model.Reservation;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

/**
 * Service pour la gestion des bookings.
 */
@Stateless
public class ReservationService {

    @Inject
    private ReservationDao reservationDao;

    @Inject
    private ClientDao clientDao;

    public Reservation saveOrUpdate(Reservation reservation) {
        if (reservation.getId() != null && !reservationDao.existsById(reservation.getId())) {
            throw new IllegalArgumentException("Aucune réservation trouvée pour l'identifiant : " + reservation.getId());
        }
        if (reservation.getClient() == null || !clientDao.existsById(reservation.getClient().getId())) {
            throw new IllegalArgumentException("Aucun client trouvé pour l'identifiant : " + reservation.getClient().getId());
        }
        return reservationDao.save(reservation);
    }

    public Reservation findById(Long id) {
        Reservation reservation = reservationDao.findById(id);
        if (reservation == null) {
            throw new IllegalArgumentException("Aucune réservation trouvée pour l'identifiant : " + id);
        }
        return reservation;
    }



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