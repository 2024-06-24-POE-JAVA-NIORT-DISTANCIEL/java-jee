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
	public void testDao1() {
		Restaurant restaurant =  restaurantDao.findRestaurantById(1);
		Assertions.assertNull(restaurant);

	}


}
