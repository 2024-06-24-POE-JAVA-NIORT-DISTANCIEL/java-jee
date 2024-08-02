package com.bigcorp.booking.dao;

import java.util.List;

import com.bigcorp.booking.correction.model.Restaurant;
import com.bigcorp.booking.model.Client;
import com.bigcorp.booking.rest.JaxRsActivator;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.persistence.NoResultException;

import javax.sql.DataSource;

/**
 * DAO s'appuyant sur un entityManager JPA
 */
@Stateless
public class ClientDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Trouve un client par son ID
     * @param id l'identifiant du client
     * @return le client trouvé ou null s'il n'existe pas
     */
    @TransactionAttribute
    public Client findById(Integer id) {
        try {
            return entityManager.find(Client.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Récupère tous les clients en base à partir de leur nom (insensible à la casse)
     * @param name
     * @return
     */
    public List<Client> findByName(String name){
        TypedQuery<Client> query = entityManager.createQuery(
                "select r from Client r where LOWER(r.name) = LOWER(:name)", Client.class);
        query.setParameter("name", name);
        List<Client> resultat = query.getResultList();
        return resultat;
    }

    /**
     * Récupère tous les clients en base à partir de leur nom (insensible à la casse)
     * @param name
     * @return
     */
    public List<Client> findByNameLike(String name){
        TypedQuery<Client> query = entityManager.createQuery(
                "select r from Client r where lower(r.name) like lower(:name)", Client.class);
        query.setParameter("name", "%" + name + "%");
        List<Client> resultat = query.getResultList();
        return resultat;
    }


    /**
     * Trouve tous les clients
     * @return la liste des clients
     */
    @TransactionAttribute
    public List<Client> findAll() {
        return entityManager.createQuery("SELECT c FROM Client c", Client.class)
                .getResultList();
    }

    /**
     * Sauvegarde ou met à jour un client
     * @param client le client à sauvegarder ou mettre à jour
     * @return le client sauvegardé avec un ID généré
     */
    @TransactionAttribute
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
    @TransactionAttribute
    public void deleteById(Integer id) {
        Client client = entityManager.find(Client.class, id);
        if (client != null) {
            entityManager.remove(client);
            return;
        }
        entityManager.remove(client);
    }
}