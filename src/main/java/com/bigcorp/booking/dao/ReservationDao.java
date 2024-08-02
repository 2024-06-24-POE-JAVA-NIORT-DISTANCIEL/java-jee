package com.bigcorp.booking.dao;

import com.bigcorp.booking.model.Reservation;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class ReservationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @TransactionAttribute
    public Reservation findById(Integer id) {
        return entityManager.find(Reservation.class, id);
    }

    public List<Reservation> findAll() {
        TypedQuery<Reservation> query = entityManager.createQuery("SELECT r FROM Reservation r", Reservation.class);
        return query.getResultList();
    }

    @TransactionAttribute
    public Reservation save(Reservation reservation) {
        return entityManager.merge(reservation);
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

    public Reservation findReservationWithClient(Integer id) {
        TypedQuery<Reservation> query = entityManager.createQuery(
                "SELECT r FROM Reservation r INNER JOIN FETCH r.client " +
                        "WHERE r.id = :id", Reservation.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}