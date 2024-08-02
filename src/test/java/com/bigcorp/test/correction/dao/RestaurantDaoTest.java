package com.bigcorp.test.correction.dao;

import com.bigcorp.booking.correction.dao.RestaurantDao;
import com.bigcorp.booking.correction.model.Prix;
import com.bigcorp.booking.correction.model.Restaurant;
import com.bigcorp.booking.dao.RestaurantTypeDao;
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

import java.time.LocalDateTime;
import java.util.List;

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
	@Inject
	private RestaurantTypeDao restaurantTypeDao;

	@Test
	public void testSaveAndGet(){
		//Création du restaurant
		Restaurant restaurant = new Restaurant();
		restaurant.setAdresse("13 rue du général Machin");
		restaurant.setNom("La taverne du général Machin");
		restaurant.setPrix(Prix.MOYEN);
		restaurant.setOuverture(LocalDateTime.of(2023,12,12,6,10,0));
		//restaurant.setAdresseDuPatron("15 avenue des lilas");

		//Sauvegarde du restaurant
		Restaurant restaurantSauvegarde = restaurantDao.save(restaurant);

		//Validations de la sauvegarde
		Assertions.assertNotNull(restaurantSauvegarde);
		Assertions.assertNotNull(restaurantSauvegarde.getId());
		System.out.println("L'identifiant du restaurant sauvegardé " + restaurantSauvegarde.getId());

		//Récupération du restaurant de la base de données avec le même identifiant
		Restaurant restaurantCharge = restaurantDao.findRestaurantById(restaurantSauvegarde.getId());

		Assertions.assertNotNull(restaurantCharge);
		Assertions.assertEquals(restaurant.getAdresse(), restaurantCharge.getAdresse());
		Assertions.assertEquals("La taverne du général Machin", restaurantCharge.getNom());
		Assertions.assertEquals(restaurant.getPrix(), restaurantCharge.getPrix());

	}

	@Test
	public void testSaveAndFindByNom(){
		//ARRANGE
		//Création du restaurant
		Restaurant restaurant = new Restaurant();
		restaurant.setNom("La bonne auberge");
		Restaurant restaurantSauvegarde = restaurantDao.save(restaurant);

		//ACT
		//Récupération du restaurant de la base de données avec le bon nom
		List<Restaurant> resultat = restaurantDao.findByNom(restaurant.getNom());

		//ASSERT
		Assertions.assertEquals(1, resultat.size());
		Restaurant restaurantCharge = resultat.get(0);
		Assertions.assertEquals(restaurant.getNom(), restaurantCharge.getNom());
	}

	@Test
	public void testSaveAndFindByNomLike(){
		//Création du restaurant
		Restaurant restaurant = new Restaurant();
		restaurant.setNom("La table de Monsieur Malinou");
		Restaurant restaurantSauvegarde = restaurantDao.save(restaurant);

		//Récupération du restaurant de la base de données avec le même identifiant
		List<Restaurant> resultat = restaurantDao.findByNomLike("malinou");

		Assertions.assertEquals(1, resultat.size());
		Restaurant restaurantCharge = resultat.get(0);

		Assertions.assertEquals(restaurant.getNom(), restaurantCharge.getNom());
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

	@Test
	public void testDeleteWithJpql(){
		//Création du restaurant
		Restaurant restaurant = new Restaurant();
		restaurant.setNom("La taverne du général Machin");

		//Sauvegarde du restaurant
		Restaurant restaurantSauvegarde = restaurantDao.save(restaurant);

		//Suppression du restaurant
		restaurantDao.deleteByIdWithJpql(restaurantSauvegarde.getId());

		Restaurant restaurantEnBase = restaurantDao.findRestaurantById(restaurantSauvegarde.getId());
		Assertions.assertNull(restaurantEnBase);
	}

	@Test
	public void testSaveWithRestaurantType(){
		//Création du restaurantType
		RestaurantType restaurantType = new RestaurantType();
		restaurantType.setName("Taverne suisse");
		RestaurantType restaurantTypeSauvegarde = restaurantTypeDao.save(restaurantType);

		//Création du restaurant
		Restaurant restaurant = new Restaurant();
		restaurant.setNom("La table des Alpes");
		restaurant.setRestaurantType(restaurantTypeSauvegarde);

		//Sauvegarde du restaurant
		Restaurant restaurantSauvegarde = restaurantDao.save(restaurant);

		//Assertion qu'une ligne a été insérée
		Restaurant restaurantCharge = restaurantDao.findRestaurantWithRestaurantTypeById(restaurantSauvegarde.getId());
		Assertions.assertNotNull(restaurantCharge);
		Assertions.assertNotNull(restaurantCharge.getRestaurantType().getName());

		

	}

}
