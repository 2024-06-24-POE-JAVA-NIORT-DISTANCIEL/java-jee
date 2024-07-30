package com.bigcorp.booking.correction.service;

import com.bigcorp.booking.correction.jsf.TypeRestaurantFormBean;
import jakarta.ejb.Stateless;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe de service : fournit du code intelligent
 * pour TypeRestaurant
 */
@Stateless
public class TypeRestaurantService {

    private int compteur = 1;
    private Map<Integer, TypeRestaurantFormBean> referentielDeDonnees = new HashMap<>();

    /**
     * Renvoie un {@link TypeRestaurantFormBean} à partir de son id
     * Peut renvoyer null si aucun {@link TypeRestaurantFormBean} ne correspond à l'id
     * @param id
     * @return
     */
    public TypeRestaurantFormBean load(Integer id){
        return this.referentielDeDonnees.get(id);
    }

    /**
     * Sauvegarde un {@link TypeRestaurantFormBean}.
     * Ecrase le précédent TypeRestaurantFormBean avec le même id.
     * @param typeRestaurantFormBean non null
     * @return
     */
    public TypeRestaurantFormBean save(TypeRestaurantFormBean typeRestaurantFormBean){
        System.out.println("Sauvegarde du restaurant dans le service");
        if(typeRestaurantFormBean.getId() == null){
            typeRestaurantFormBean.setId(compteur++);
        }
        this.referentielDeDonnees.put(typeRestaurantFormBean.getId(), typeRestaurantFormBean);
        return typeRestaurantFormBean;
    }

}
