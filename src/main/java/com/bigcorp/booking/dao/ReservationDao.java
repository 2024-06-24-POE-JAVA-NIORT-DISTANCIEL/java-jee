package com.bigcorp.booking.dao;

import com.bigcorp.booking.model.Reservation;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.NoResultException;
import java.util.List;

@Stateless
public class ReservationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @TransactionAttribute
    public Reservation findById(Integer id) {
        try {
            return entityManager.find(Reservation.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Reservation> findAll() {
        return entityManager.createQuery("SELECT r FROM Reservation r", Reservation.class)
                .getResultList();
    }

    @TransactionAttribute
    public Reservation save(Reservation reservation) {
        if (reservation.getId() == null) {
            entityManager.persist(reservation);
            return reservation;
        } else {
            return entityManager.merge(reservation);
        }
    }

    @TransactionAttribute
    public void deleteById(Integer id) {
        Reservation reservation = entityManager.find(Reservation.class, id);
        if (reservation != null) {
            entityManager.remove(reservation);
        }
    }

    public List<Reservation> findByClientName(String clientName) {
        TypedQuery<Reservation> query = entityManager.createQuery(
                "SELECT r FROM Reservation r WHERE r.client.name = :clientName", Reservation.class);
        query.setParameter("clientName", clientName);
        return query.getResultList();
    }
}