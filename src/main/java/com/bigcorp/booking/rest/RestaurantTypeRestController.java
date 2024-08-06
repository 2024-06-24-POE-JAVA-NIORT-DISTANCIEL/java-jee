package com.bigcorp.booking.rest;


import java.util.List;

import com.bigcorp.booking.model.RestaurantType;
import com.bigcorp.booking.service.RestaurantTypeService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
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
    public List<RestaurantTypeDto> listAll() {
        List<RestaurantTypeDto> allRestaurants = this.restaurantTypeService.findAll();
        return allRestaurants;
    }

    /**
	 * Traite les requêtes GET /{id}
	 * @return
	 */
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestaurantTypeDto getById(@PathParam("id") Long id) {
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
    public RestaurantTypeDto save(RestaurantTypeDto restaurantTypeDto) {
        return this.restaurantTypeService.save(restaurantTypeDto);
    }
  
}