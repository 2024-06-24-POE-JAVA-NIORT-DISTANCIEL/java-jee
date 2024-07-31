package com.bigcorp.booking.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class RestaurantBean {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String description;

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Méthode pour charger les données du restaurant
    public void loadData() {
        // Simuler la récupération des données du restaurant basé sur l'ID
        if (id == null || id.isEmpty()) {
            name = "Restaurant Inconnu";
            description = "Aucune description disponible";
        } else {
            switch (id) {
                case "1":
                    name = "Le Gourmet";
                    description = "Français";
                    break;
                case "2":
                    name = "La Dolce Vita";
                    description = "Italien";
                    break;
                case "3":
                    name = "Sakura";
                    description = "Japonais";
                    break;
                case "4":
                    name = "El Toro";
                    description = "Espagnol";
                    break;
                case "5":
                    name = "The Curry House";
                    description = "Indien";
                    break;
                case "6":
                    name = "Osteria";
                    description = "Italien";
                    break;
                case "7":
                    name = "Won Ton";
                    description = "Cantonnais";
                    break;
                case "8":
                    name = "Le Bistro";
                    description = "Français";
                    break;
                case "9":
                    name = "Tacos Locos";
                    description = "Mexicain";
                    break;
                case "10":
                    name = "Le zouglou dans ta bouche";
                    description = "Ivoirien";
                    break;
                default:
                    name = "Restaurant Inconnu";
                    description = "Aucune description disponible";
                    break;
            }
        }
    }
}
