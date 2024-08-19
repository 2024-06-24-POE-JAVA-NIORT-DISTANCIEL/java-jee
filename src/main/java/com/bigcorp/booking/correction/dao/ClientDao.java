package com.bigcorp.booking.correction.dao;

import com.bigcorp.booking.correction.model.Client;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ClientDao {
    @PersistenceContext
    EntityManager entityManager;

    public Client getById(Integer id) {
        return entityManager.find(Client.class, id);
    }

    public Client save(Client client) {
        Client monClient = entityManager.merge(client);

        return monClient;
    }

    @TransactionAttribute
    public void deleteById(Integer id) {
        Client monClient = entityManager.find(Client.class, id);

        if (monClient == null) {
            return;
        }

        entityManager.remove(monClient);
    }
}