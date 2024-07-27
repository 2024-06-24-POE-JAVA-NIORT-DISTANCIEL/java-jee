package com.bigcorp.booking.correction.servlet.model;

import jakarta.servlet.ServletContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Gère un stock dans le servletContext.
 * Aurait pu être transformé en Singleton
 */
public class Stock {

    private static final String STOCK_NAME = "stock";

    /**
     * Renvoie le stock dans la servletContext.
     * Crée le stock s'il n'existe pas.
     */
    public static Map<Integer, Serviette> getStock(ServletContext servletContext){
        Map<Integer, Serviette> stock = (Map<Integer, Serviette>) servletContext.getAttribute(STOCK_NAME);
        if(stock == null){
            stock = initStock();
            servletContext.setAttribute(STOCK_NAME, stock);
        }
        return stock;
    }

    /**
     * Crée un nouveau stock (une map)
     * @return la map créée
     */
    private static Map<Integer, Serviette > initStock(){
        Map<Integer, Serviette> stock = new HashMap<>();
        Serviette servietteDePlage = new Serviette();
        servietteDePlage.setId(1);
        servietteDePlage.setNom("Serviette de plage");
        stock.put(servietteDePlage.getId(), servietteDePlage);

        Serviette servietteDeTable = new Serviette();
        servietteDeTable.setId(2);
        servietteDeTable.setNom("Serviette de table");
        stock.put(servietteDeTable.getId(), servietteDeTable);

        Serviette servietteDeBain = new Serviette();
        servietteDeBain.setId(3);
        servietteDeBain.setNom("Serviette de bain");
        stock.put(servietteDeBain.getId(), servietteDeBain);
        return stock;
    }


}
