package com.bigcorp.booking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Entité : décrit des données persistées dans une classe (un POJO)
 */
@Entity
public class RestaurantType {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Le nom est obligatoire.")
	@Size(min = 3, max = 30, message = "Le nom doit comporter entre 3 et 30 caractères.")
	private String name;

	/**
	 * Cette relation OneToMany est l'inverse
	 * de la relation restaurantType de l'entité Restaurant.
	 * Elle est configurée côté Restaurant (voir Restaurant.restaurantType).
	 * Modifier cette collection et persister RestaurantType NE sauvegardera PAS la relation.
	 */
	@OneToMany(mappedBy = "restaurantType")
	private Set<Restaurant> restaurants = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Set<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
}