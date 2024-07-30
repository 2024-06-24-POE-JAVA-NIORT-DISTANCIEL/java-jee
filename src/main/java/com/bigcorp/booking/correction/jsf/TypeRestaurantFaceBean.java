package com.bigcorp.booking.correction.jsf;

import com.bigcorp.booking.correction.service.TypeRestaurantService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.NamedEntityGraph;

import java.io.Serializable;

@Named
@RequestScoped
public class TypeRestaurantFaceBean {

    private Integer id;

    private TypeRestaurantFormBean typeRestaurantFormBean = new TypeRestaurantFormBean();

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

    public void onLoad(){
        System.out.println("Je charge un type de restaurant, avec l'id : " + id);
        this.typeRestaurantFormBean =  this.typeRestaurantService.load(id);
    }

    public String save(){
        System.out.println("Sauvegarde du restaurant dans le FaceBean");
        System.out.println("L'id vaut : " + this.typeRestaurantFormBean.getId());
        System.out.println("Le nom vaut : " + this.typeRestaurantFormBean.getNom());
        System.out.println("Le restaurant est actif : " + this.typeRestaurantFormBean.getActif());
        this.typeRestaurantService.save(this.typeRestaurantFormBean);
        return "gestion-type-restaurant?faces-redirect=true&id=" + this.typeRestaurantFormBean.getId();
    }
    
}

