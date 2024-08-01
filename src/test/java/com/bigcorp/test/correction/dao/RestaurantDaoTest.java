package com.bigcorp.test.correction.dao;

import com.bigcorp.booking.correction.dao.RestaurantDao;
import com.bigcorp.booking.correction.model.Restaurant;
import com.bigcorp.booking.model.RestaurantType;
import com.bigcorp.booking.service.RestaurantTypeService;
import jakarta.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Teste le DAO. Utilise une base en mémoire
 */
@ExtendWith(ArquillianExtension.class)
public class RestaurantDaoTest {

	/**
	 * Initialise le serveur JEE
	 * @return
	 */
	@Deployment
	public static WebArchive createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "it.war")
				.addPackages(true, "com.bigcorp.booking")
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	/**
	 * Si le serveur JEE est bien initialisé, on peut injecter ici
	 * nos objets. Ici, on injecte le DAO pour le tester.
	 */
	@Inject
	private RestaurantDao restaurantDao;

	@Test
	public void testSaveAndGet(){
		//Création du restaurant
		Restaurant restaurant = new Restaurant();
		restaurant.setAdresse("13 rue du général Machin");
		restaurant.setNom("La taverne du général Machin");
		//restaurant.setAdresseDuPatron("15 avenue des lilas");

		//Sauvegarde du restaurant
		Restaurant restaurantSauvegarde = restaurantDao.save(restaurant);

		//Validations de la sauvegarde
		Assertions.assertNotNull(restaurantSauvegarde);
		Assertions.assertNotNull(restaurantSauvegarde.getId());
		System.out.println("L'identifiant du restaurant sauvegardé " + restaurantSauvegarde.getId());

		//Récupération du restaurant de la base de données avec le même identifiant
		Restaurant restaurantCharge = restaurantDao.findRestaurantById(restaurantSauvegarde.getId());

		Assertions.assertEquals("13 rue du général Machin", restaurantCharge.getAdresse());
		Assertions.assertEquals("La taverne du général Machin", restaurantCharge.getNom());
	}

	@Test
	public void testDelete(){
		//Création du restaurant
		Restaurant restaurant = new Restaurant();
		restaurant.setNom("La taverne du général Machin");

		//Sauvegarde du restaurant
		Restaurant restaurantSauvegarde = restaurantDao.save(restaurant);
		Restaurant restaurantEnBase = restaurantDao.findRestaurantById(restaurantSauvegarde.getId());
		Assertions.assertNotNull(restaurantEnBase);

		//Suppression du restaurant
		restaurantDao.delete(restaurantSauvegarde.getId());

		restaurantEnBase = restaurantDao.findRestaurantById(restaurantSauvegarde.getId());
		Assertions.assertNull(restaurantEnBase);
	}

}
