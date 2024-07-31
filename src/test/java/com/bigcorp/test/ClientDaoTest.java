package com.bigcorp.test.dao;

import com.bigcorp.booking.dao.ClientDao;
import com.bigcorp.booking.model.Client;
import jakarta.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

/**
 * Teste le DAO. Utilise une base en mémoire
 */
@ExtendWith(ArquillianExtension.class)
public class ClientDaoTest {

    /**
     * Initialise le serveur JEE
     * @return WebArchive contenant les ressources nécessaires pour les tests
     */
    @Deployment
    public static WebArchive createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "ClientDaoTest.war")
                .addPackages(true, "com.bigcorp.booking")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    /**
     * Injecte le DAO pour le tester
     */
    @Inject
    private ClientDao clientDao;

    @Test
    public void testFindAll() {
        // Create and save two clients
        Client client1 = new Client();
        client1.setName("Alice");
        client1.setEmail("alice@example.com");
        client1.setPhone("9876543210");
        client1.setAddress("456 Oak Avenue");
        client1.setAge(25);
        client1.setPremium(false);
        clientDao.save(client1);

        Client client2 = new Client();
        client2.setName("Bob");
        client2.setEmail("bob@example.com");
        client2.setPhone("5555555555");
        client2.setAddress("789 Pine Road");
        client2.setAge(35);
        client2.setPremium(true);
        clientDao.save(client2);

        // Find all clients
        List<Client> clients = clientDao.findAll();
        Assertions.assertTrue(clients.size() >= 2, "There should be at least 2 clients in the database");
    }

    @Test
    public void testFindById() {
        // Create and save a new client
        Client newClient = new Client();
        newClient.setName("John Doe");
        newClient.setEmail("john.doe@example.com");
        newClient.setPhone("1234567890");
        newClient.setAddress("123 Elm Street");
        newClient.setAge(30);
        newClient.setPremium(true);
        Client savedClient = clientDao.save(newClient);

        // Find the client by ID
        Client foundClient = clientDao.findById(savedClient.getId());
        Assertions.assertNotNull(foundClient, "Client should be found by ID");
        Assertions.assertEquals("John Doe", foundClient.getName(), "Client name should match");
    }

    @Test
    public void testCreate() {
        Client newClient = new Client();
        newClient.setName("Jane Doe");
        newClient.setEmail("jane.doe@example.com");
        newClient.setPhone("0987654321");
        newClient.setAddress("321 Maple Street");
        newClient.setAge(28);
        newClient.setPremium(false);

        Client savedClient = clientDao.save(newClient);

        Assertions.assertNotNull(savedClient.getId(), "Client ID should not be null after saving");
        Assertions.assertEquals("Jane Doe", savedClient.getName(), "Client name should match");
    }

    @Test
    public void testUpdateById() {
        // Create and save a new client
        Client client = new Client();
        client.setName("Michael Smith");
        client.setEmail("michael.smith@example.com");
        client.setPhone("1111111111");
        client.setAddress("456 Birch Road");
        client.setAge(40);
        client.setPremium(true);
        Client savedClient = clientDao.save(client);

        // Update the client
        savedClient.setPhone("2222222222");
        savedClient.setAddress("789 Cedar Avenue");
        Client updatedClient = clientDao.save(savedClient);

        // Verify the update
        Client foundClient = clientDao.findById(updatedClient.getId());
        Assertions.assertNotNull(foundClient, "Client should be found after update");
        Assertions.assertEquals("2222222222", foundClient.getPhone(), "Client phone should be updated");
        Assertions.assertEquals("789 Cedar Avenue", foundClient.getAddress(), "Client address should be updated");
    }

    @Test
    public void testDeleteById() {
        // Create and save a client
        Client client = new Client();
        client.setName("Emily Davis");
        client.setEmail("emily.davis@example.com");
        client.setPhone("3333333333");
        client.setAddress("1010 Spruce Lane");
        client.setAge(33);
        client.setPremium(false);
        Client savedClient = clientDao.save(client);

        // Ensure the client is saved
        Assertions.assertNotNull(savedClient.getId(), "Client ID should not be null");

        // Delete the client
        clientDao.deleteById(savedClient.getId());

        // Ensure the client is deleted
        Client deletedClient = clientDao.findById(savedClient.getId());
        Assertions.assertNull(deletedClient, "Client should be null after deletion");
    }

}
