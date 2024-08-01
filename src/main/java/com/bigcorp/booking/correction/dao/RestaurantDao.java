package com.bigcorp.booking.correction.dao;

import com.bigcorp.booking.correction.model.Restaurant;
import com.bigcorp.booking.rest.JaxRsActivator;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * DAO : Data Access Object : classe
 * dont le rôle est d'accéder aux données en lecture/écriture.
 * Celle-ci sert pour l'entité Restaurant
 */
@Stateless
public class RestaurantDao {

    /**
     * L'EntityManager provient
     * du serveur JEE. C'est lui qui l'instancie et qui nous l'injecte.
     */
    @PersistenceContext
    private EntityManager entityManager;


    /**
     * Récupère un restaurant à partir de son identifiant
     * (sa clé primaire) : utilise une méthode d'EntityManager pour ce faire.
     * @param id
     * @return
     */
    public Restaurant findRestaurantById(Integer id){
       return  entityManager.find(Restaurant.class, id );
    }

    /**
     * Sauvegarde un restaurant
     * @param restaurant
     * @return le restaurant sauvegardé
     */
    @TransactionAttribute
    public Restaurant save(Restaurant restaurant){
        return entityManager.merge(restaurant);
    }

    /**
     * Supprime un restaurant par son id.
     * Ne fait rien si le restaurant n'existe pas en base
     * @param id
     */
    @TransactionAttribute
    public void delete(Integer id){
        Restaurant restaurant = entityManager.find(Restaurant.class, id);
        if(restaurant == null){
            return;
        }
        entityManager.remove(restaurant);
    }

}