package com.bigcorp.booking.correction.dao;

import java.util.List;

import com.bigcorp.booking.correction.model.Reservation;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class ReservationDao {
    @PersistenceContext
    EntityManager entityManager;

    public Reservation getBydId(Integer id) {
        Reservation maReservation = entityManager.find(Reservation.class, id);

        return maReservation;
    }

    public List<Reservation> getAll() {
        TypedQuery<Reservation> query = entityManager.createQuery("SELECT DISTINCT r from Reservation r", Reservation.class);

        return query.getResultList();
    }

    public Reservation save(Reservation reservation) {
        Reservation maReservation = entityManager.merge(reservation);

        return maReservation;
    }

    @TransactionAttribute
    public void deleteById(Integer id) {
        Reservation maReservation = entityManager.find(Reservation.class, id);

        if (maReservation != null) {
            entityManager.remove(maReservation);
        }
    }
}
