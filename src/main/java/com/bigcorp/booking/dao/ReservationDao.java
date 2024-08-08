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
    EntityManager entityManager;

    public Reservation findById(Long id) {return entityManager.find(Reservation.class, id);}

    public List<Reservation> findAll() {
        return this.entityManager.createQuery("select distinct r from Reservation r", Reservation.class)
                .getResultList();
    }

    public Reservation findReservationByClientLikeName(String name) {
        TypedQuery<Reservation> query = entityManager.createQuery(
                "select r from Reservation r where r.client.name like :name", Reservation.class);
        query.setParameter("name", "%" + name + "%");
        return query.getSingleResult();
    }

    @TransactionAttribute
    public Reservation save(Reservation reservation) {
        return entityManager.merge(reservation);
    }

    @TransactionAttribute
    public void deleteById(Long id) {
        Reservation reservation = entityManager.find(Reservation.class, id);
        if (reservation != null) {
            entityManager.remove(reservation);
        }
    }

    public List<Reservation> findByClientName(String clientName) {
        TypedQuery<Reservation> query = entityManager.createQuery(
                "select r from Reservation r where r.client.name = :clientName", Reservation.class);
        query.setParameter("clientName", clientName);
        return query.getResultList();
    }

    public Reservation findReservationByClient(Long id) {
        TypedQuery<Reservation> query = entityManager.createQuery(
                "select r from Reservation r inner join fetch r.client " +
                        "where r.id = :id", Reservation.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<String> findReservationEmailsByClientId(Long clientId) {
        TypedQuery<String> query = entityManager.createQuery(
                "select r.client.email from Reservation r where r.client.id = :clientId", String.class);
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }
}