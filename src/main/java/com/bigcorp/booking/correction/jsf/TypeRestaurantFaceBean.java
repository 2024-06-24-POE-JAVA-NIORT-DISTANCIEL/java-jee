package com.bigcorp.booking.correction.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.NamedEntityGraph;

@Named
@RequestScoped
public class TypeRestaurantFaceBean {

    private String id;

    private TypeRestaurantFormBean typeRestaurantFormBean = new TypeRestaurantFormBean();

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        this.typeRestaurantFormBean = new TypeRestaurantFormBean();
        this.typeRestaurantFormBean.setId(this.id);
    }

    public void save(){
        System.out.println("Je sauvegarde le restaurant");
        System.out.println("L'id vaut : " + this.typeRestaurantFormBean.getId());
        System.out.println("Le nom vaut : " + this.typeRestaurantFormBean.getNom());
    }
}
