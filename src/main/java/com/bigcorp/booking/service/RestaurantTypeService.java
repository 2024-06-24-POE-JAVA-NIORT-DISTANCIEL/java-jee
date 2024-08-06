package com.bigcorp.booking.service;

import java.util.List;

import org.jboss.logging.Logger;

import com.bigcorp.booking.dao.RestaurantTypeDao;
import com.bigcorp.booking.model.RestaurantType;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.inject.Inject;

/**
 * Service de gestion des RestaurantType
 * Contient le code fonctionnel des RestaurantType
 */
@Stateless
public class RestaurantTypeService {
	
	private static final Logger LOGGER = Logger.getLogger(RestaurantTypeService.class);

	@Inject
	private RestaurantTypeDao restaurantTypeDao;

	public RestaurantType findById(Long id) {
		return this.restaurantTypeDao.findById(id);
	}

	public RestaurantType findWithRestaurantsById(Long id) {
		return this.restaurantTypeDao.findWithRestaurantsById(id);
	}

	@TransactionAttribute
	public RestaurantType save(RestaurantType restaurantType) {
		LOGGER.info("Sauvegarde de : " + restaurantType);
		return this.restaurantTypeDao.save(restaurantType);
	}

//	@TransactionAttribute
//	public RestaurantType update(RestaurantType restaurantType) {
//		LOGGER.info("Mise à jour de : " + restaurantType);
//		// Vérifiez si l'entité existe avant de mettre à jour
//		RestaurantType existing = this.restaurantTypeDao.findById(restaurantType.getId());
//		if (existing == null) {
//			throw new IllegalArgumentException("RestaurantType not found for ID: " + restaurantType.getId());
//		}
//		return this.restaurantTypeDao.update(restaurantType);
//	}

	public List<RestaurantType> findLikeName(String restaurantTypeFilter) {
		return this.restaurantTypeDao.findLikeName(restaurantTypeFilter);
	}

	public List<RestaurantType> findAll() {
		return this.restaurantTypeDao.findAll();
	}

	public void deleteById(Long id) {
		this.restaurantTypeDao.deleteById(id);
	}

}
