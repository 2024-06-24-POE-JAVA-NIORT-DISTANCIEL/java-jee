package com.bigcorp.booking.dao;

import com.bigcorp.booking.model.Client;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * DAO pour les opérations CRUD sur Client avec JPA.
 */
@Stateless
public class ClientDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Trouve un client par son ID.
     * @param id l'identifiant du client
     * @return le client trouvé ou null s'il n'existe pas
     */
    @Transactional
    public Client findById(Long id) {
        return entityManager.find(Client.class, id);
    }

    /**
     * Récupère tous les clients en base à partir de leur nom (insensible à la casse).
     * @param nom le nom du client
     * @return la liste des clients trouvés
     */
    public List<Client> findByName(String nom) {
        TypedQuery<Client> query = entityManager.createQuery(
                "SELECT c FROM Client c WHERE LOWER(c.nom) = LOWER(:nom)", Client.class);
        query.setParameter("nom", nom);
        return query.getResultList();
    }

    /**
     * Récupère tous les clients en base à partir de leur nom (insensible à la casse).
     * @param nom le nom du client (partiel)
     * @return la liste des clients trouvés
     */
    public List<Client> findByNameLike(String nom) {
        TypedQuery<Client> query = entityManager.createQuery(
                "SELECT c FROM Client c WHERE LOWER(c.nom) LIKE LOWER(:nom)", Client.class);
        query.setParameter("nom", "%" + nom + "%");
        return query.getResultList();
    }

    /**
     * Trouve tous les clients.
     * @return la liste de tous les clients
     */
    @Transactional
    public List<Client> findAll() {
        return entityManager.createQuery("SELECT c FROM Client c", Client.class).getResultList();
    }

    /**
     * Sauvegarde ou met à jour un client.
     * @param client le client à sauvegarder ou mettre à jour
     * @return le client sauvegardé ou mis à jour
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
     * Vérifie si un client existe par son ID.
     * @param id l'identifiant du client
     * @return true si le client existe, false sinon
     */
    public boolean existsById(Long id) {
        return entityManager.find(Client.class, id) != null;
    }
    /**
     * Supprime un client par son ID.
     * @param id l'identifiant du client à supprimer
     */
    @Transactional
    public void deleteById(Long id) {
        Client client = entityManager.find(Client.class, id);
        if (client != null) {
            entityManager.remove(client);
        }
    }
}