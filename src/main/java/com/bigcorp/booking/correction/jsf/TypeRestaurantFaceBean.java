package com.bigcorp.booking.correction.jsf;

import com.bigcorp.booking.correction.service.TypeRestaurantService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.NamedEntityGraph;

import java.io.Serializable;
import java.util.Collection;

/**
 * Cette classe est annotée avec @Named et @RequestScoped : elle
 * sera instanciée par JSF une fois pour chaque requête.
 */
@Named
@RequestScoped
public class TypeRestaurantFaceBean {

    private Integer id;

    private TypeRestaurantFormBean typeRestaurantFormBean = new TypeRestaurantFormBean();

    /**
     * On peut injecter toute instance qui est connue par Jakarta CDI
     */
    @Inject
    private TypeRestaurantService typeRestaurantService;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeRestaurantFormBean getTypeRestaurantFormBean() {
        return typeRestaurantFormBean;
    }

    public void setTypeRestaurantFormBean(TypeRestaurantFormBean typeRestaurantFormBean) {
        this.typeRestaurantFormBean = typeRestaurantFormBean;
    }

    /**
     * Getter un peu artificiel qui renvoie tous les types de restaurants.
     * @return
     */
    public Collection<TypeRestaurantFormBean> getTypesRestaurants(){
        return this.typeRestaurantService.findAll();
    }

    /**
     * Méthode à appeler au chargement de la vue
     */
    public void onLoad(){
        System.out.println("Je charge un type de restaurant, avec l'id : " + id);
        this.typeRestaurantFormBean =  this.typeRestaurantService.load(id);
    }

    /**
     * Sauvegarde la vue (et plus précisèment les données du formulaire
     * présentes dans typeRestaurantFormBean.
     * @return
     */
    public String save(){
        System.out.println("Sauvegarde du restaurant dans le FaceBean");
        System.out.println("L'id vaut : " + this.typeRestaurantFormBean.getId());
        System.out.println("Le nom vaut : " + this.typeRestaurantFormBean.getNom());
        System.out.println("Le restaurant est actif : " + this.typeRestaurantFormBean.getActif());
        this.typeRestaurantService.save(this.typeRestaurantFormBean);
        return "gestion-type-restaurant?faces-redirect=true&id=" + this.typeRestaurantFormBean.getId();
    }

    /**
     * Renvoie au tableau des types de restaurants
     * @return
     */
    public String cancel(){
        return "tableau-types-restaurants?faces-redirect=true";
    }

}

