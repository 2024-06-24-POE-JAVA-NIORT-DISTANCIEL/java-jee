package com.bigcorp.booking.correction.servlet.model;

import java.util.*;

/**
 * Panier contenant des serviette
 */
public class Panier {

    private List<Serviette> serviettes = new ArrayList<>();

    public void add(Serviette serviette){
        this.serviettes.add(serviette);
    }

    /**
     * Utilisation du polymorphisme :
     * on renvoie ici un iterable pour éviter
     * que les appels à getAll() ne modifient la liste.
     * getAll() renvoie un Iterable qui ne sert qu'à être itéré.
     * Cela fonctionne car List hérite de Collection et Collection hérite d'Iterable :
     * une List est un Iterable.
     * @return
     */
    public Iterable<Serviette> getAll(){
        return this.serviettes;
    }
}
