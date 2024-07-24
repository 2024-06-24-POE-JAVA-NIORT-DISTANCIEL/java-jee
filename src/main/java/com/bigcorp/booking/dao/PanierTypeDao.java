//package com.bigcorp.booking.dao;
//
//import com.bigcorp.booking.model.Panier;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//
//import java.util.List;
//
//@ApplicationScoped
//public class PanierTypeDao {
//
//    @PersistenceContext
//    EntityManager entityManager;
//
//    // Afficher tous les paniers
//    public List<Panier> findAll() {
//        return this.entityManager
//                .createQuery("select p from com.bigcorp.booking.model.Panier p", Panier.class)
//                .getResultList();
//    }
//
//    // Afficher un panier par ID
//    public Panier findById(Long id) {
//        return entityManager.find(Panier.class, id);
//    }
//
//    // Ajouter un panier (si le panier n'existe pas encore)
//    public Panier save(Panier panier) {
//        if (panier.getId() == null) {
//            entityManager.persist(panier);
//            return panier;
//        } else {
//            return entityManager.merge(panier);
//        }
//    }
//
//    // Supprimer un panier par son ID
//    public void deleteById(Long id) {
//        Panier entity = entityManager.find(Panier.class, id);
//        if (entity == null) {
//            return;
//        }
//        entityManager.remove(entity);
//    }
//
//    // Ajouter un article à un panier
//    public void addArticleToPanier(Long panierId, Long articleId) {
//        Panier panier = entityManager.find(Panier.class, panierId);
//        if (panier == null) {
//            return;
//        }
//        Article article = entityManager.find(Article.class, articleId);
//        if (article == null) {
//            return;
//        }
//        panier.addArticle(article);
//        entityManager.merge(panier);
//    }
//}