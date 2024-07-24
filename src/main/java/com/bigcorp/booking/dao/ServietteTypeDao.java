package com.bigcorp.booking.dao;

import com.bigcorp.booking.model.ServietteType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ServietteTypeDao {

    @PersistenceContext
    EntityManager entityManager;

    //afficher tous les produits
    public List<ServietteType> findAll() {
        return this.entityManager
                .createQuery("select distinct r from ServietteType r", ServietteType.class)
                .getResultList();
    }

    //afficher par id
    public ServietteType findById(Long id) {
        return entityManager.find(ServietteType.class, id);
    }

    //afficher type serviette par id
    public ServietteType findWithServiettesById(Long id) {
        List<ServietteType> result = this.entityManager.createQuery(
                "select distinct r from ServietteType r left outer join fetch r.serviettes where s.id = :id",
                ServietteType.class).setParameter("id", id).getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    //afficher type serviette par nom
    public List<ServietteType> findLikeName(String nom) {
        if (nom == null || nom.isEmpty()) {
            return Collections.emptyList();
        }
        TypedQuery<ServietteType> query = this.entityManager.createQuery(
                "SELECT DISTINCT r FROM ServietteType r " + " WHERE upper(r.nom) like :name", ServietteType.class);
        query.setParameter("name", "%" + nom.toUpperCase() + "%");
        return query.getResultList();
    }

    //ajouter un produit
    public ServietteType save(ServietteType servietteType) {
        return entityManager.merge(servietteType);
    }

    //supprimer un produit par son id
    public void deleteById(Long id) {
        ServietteType entity = entityManager.find(ServietteType.class, id);
        if(entity == null) {
            return;
        }
        entityManager.remove(entity);
    }
}
