package com.bigcorp.booking.rest;

import java.util.List;

import com.bigcorp.booking.model.RestaurantType;
import com.bigcorp.booking.service.RestaurantTypeService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

/**
 * Un contrôleur REST de base pour /restaurant-types
 * L'URL de base est dans Path
 */
@Path("/restaurant-types")
@RequestScoped
public class RestaurantTypeRestController {

    @Inject
    private RestaurantTypeService restaurantTypeService;

    /**
     * Traite les requêtes GET /
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RestaurantType> listAll() {
        return this.restaurantTypeService.findAll();
    }

    /**
     * Traite les requêtes GET /{id}
     * @return
     */
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestaurantType getById(@PathParam("id") Long id) {
        return this.restaurantTypeService.findById(id);
    }

    /**
     * Traite les requêtes DELETE /{id}
     * @return
     */
    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public void deleteById(@PathParam("id") Long id) {
        this.restaurantTypeService.deleteById(id);
    }

    /**
     * Traite les requêtes POST /
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RestaurantType save(RestaurantType restaurantType) {
        return this.restaurantTypeService.save(restaurantType);
    }

    /**
     * Traite les requêtes PUT /{id}
     * @param id
     * @param restaurantType
     * @return
     */
    @PUT
    @Path("/{id:[0-9][0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RestaurantType update(@PathParam("id") Long id, RestaurantType restaurantType) {
        // Assurez-vous que l'id du restaurantType correspond à l'id du PathParam
        if (!id.equals(restaurantType.getId())) {
            throw new WebApplicationException("ID mismatch", 400);
        }
        return this.restaurantTypeService.save(restaurantType);
    }
}
