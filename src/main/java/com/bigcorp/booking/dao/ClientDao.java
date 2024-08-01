package com.bigcorp.booking.dao;

import java.util.List;
import com.bigcorp.booking.model.Client;
import com.bigcorp.booking.rest.JaxRsActivator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.persistence.NoResultException;

/**
 * DAO s'appuyant sur un entityManager JPA
 */
@ApplicationScoped
public class ClientDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Trouve un client par son ID
     * @param id l'identifiant du client
     * @return le client trouvé ou null s'il n'existe pas
     */
    @Transactional
    public Client findById(Integer id) {
        try {
            return entityManager.find(Client.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Trouve tous les clients
     * @return la liste des clients
     */
    @Transactional
    public List<Client> findAll() {
        return entityManager.createQuery("SELECT c FROM Client c", Client.class)
                .getResultList();
    }

    /**
     * Sauvegarde ou met à jour un client
     * @param client le client à sauvegarder ou mettre à jour
     * @return le client sauvegardé avec un ID généré
     */
    @Transactional
    public Client save(Client client) {
        if (client.getId() == null) {
            entityManager.persist(client);
            return client;
        } else {
            return entityManager.merge(client);
        }
    }

    /**
     * Supprime un client par son ID
     * @param id l'identifiant du client à supprimer
     */
    @Transactional
    public void deleteById(Integer id) {
        Client client = entityManager.find(Client.class, id);
        if (client != null) {
            entityManager.remove(client);
            return;
        }
        entityManager.remove(client);
    }
}