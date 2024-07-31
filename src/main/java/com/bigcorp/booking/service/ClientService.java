package com.bigcorp.booking.service;

import com.bigcorp.booking.jsf.ClientFormBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe de service : fournit du code intelligent
 * pour Client
 */
@Stateless
public class ClientService {

    private int compteur = 1;
    private Map<Integer, ClientFormBean> referentielDeDonnees = new HashMap<>();

    /**
     * Renvoie un {@link ClientFormBean} à partir de son id
     * Peut renvoyer null si aucun {@link ClientFormBean} ne correspond à l'id
     * @param id
     * @return
     */
    public ClientFormBean load(Integer id) {
        return this.referentielDeDonnees.get(id);
    }

    /**
     * Sauvegarde un {@link ClientFormBean}.
     * Ecrase le précédent TypeRestaurantFormBean avec le même id.
     * @param clientFormBean non null
     */
    public void save(ClientFormBean clientFormBean) {
        System.out.println("Sauvegarde les données du client");
        if (clientFormBean.getId() == null) {
            clientFormBean.setId(compteur++);
        }
        this.referentielDeDonnees.put(clientFormBean.getId(), clientFormBean);
    }
    /**
     * Renvoie tous les clients précédemment sauvegardés
     * @return
     */
    public Collection<ClientFormBean> findAll() {
        return this.referentielDeDonnees.values();
    }
}
