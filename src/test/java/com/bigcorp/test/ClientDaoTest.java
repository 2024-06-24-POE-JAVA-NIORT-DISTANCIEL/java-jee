package com.bigcorp.test;

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
    private ClientDao clientDao;


    private void printClientInfo(String action, Client client) {
        System.out.println(action + " - Client Info: ");
        System.out.println("ID: " + (client.getId() != null ? client.getId() : "Not saved yet"));
        System.out.println("Nom: " + client.getNom());
        System.out.println("Email: " + client.getEmail());
        System.out.println("Phone: " + client.getPhone());
        System.out.println("Address: " + client.getAddress());
        System.out.println("Age: " + client.getAge());
        System.out.println("Premium: " + client.isPremium());
        System.out.println("---------------");
    }

    @Test
    public void testFindAll() {
        Client client1 = new Client();
        client1.setName("Alice");
        client1.setEmail("alice@example.com");
        client1.setPhone("9876543210");
        client1.setAddress("456 Oak Avenue");
        client1.setAge(25);
        client1.setPremium(false);
        clientDao.save(client1);

        Client client2 = new Client();
        client2.setNom("Bob");
        client2.setEmail("bob@example.com");
        client2.setPhone("5555555555");
        client2.setAddress("789 Pine Road");
        client2.setAge(35);
        client2.setPremium(true);
        clientDao.save(client2);

        List<Client> clients = clientDao.findAll();
        System.out.println("Found clients: " + clients.size());
        for (Client client : clients) {
            printClientInfo("Found", client);
        }
        Assertions.assertTrue(clients.size() >= 2, "Expected at least 2 clients, but found: " + clients.size());
    }

    @Test
    public void testFindById() {
        Client client = new Client();
        client.setNom("John Doe");
        client.setEmail("john.doe@example.com");
        client.setPhone("1234567890");
        client.setAddress("123 Elm Street");
        client.setAge(30);
        client.setPremium(true);
        Client savedClient = clientDao.save(client);


        Client foundClient = clientDao.findById(savedClient.getId());
        printClientInfo("Found by ID", foundClient);
        Assertions.assertNotNull(foundClient, "Client should be found by ID, but was null");
        Assertions.assertEquals("John Doe", foundClient.getName(), "Expected name: John Doe, but found: " + foundClient.getName());
    }

    @Test
    public void testSaveAndFindByName(){
        //ARRANGE
        //Création du client
        Client client = new Client();
        client.setName("Marc");
        Client clientSauvegarde = clientDao.save(client);

        //ACT
        //Récupération du client de la base de données avec le bon nom
        List<Client> resultat = clientDao.findByName(client.getName());

        //ASSERT
        Assertions.assertEquals(1, resultat.size());
        Client clientCharge = resultat.get(0);
        Assertions.assertEquals(client.getName(), clientCharge.getName());
    }

    @Test
    public void testSaveAndFindByNameLike(){
        // Create the client
        Client client = new Client();
        client.setName("malinou");
        Client savedClient = clientDao.save(client);

        // Retrieve the client from the database with a similar name
        List<Client> result = clientDao.findByNameLike("no");

        // Assertions
        Assertions.assertFalse(result.isEmpty(), "Expected at least one client, but found none");
        Client loadedClient = result.get(0);
        Assertions.assertEquals(client.getName(), loadedClient.getName(), "Expected name: " + client.getName() + ", but found: " + loadedClient.getName());
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
        printClientInfo("Created", savedClient);

        Assertions.assertNotNull(savedClient.getId(), "Client ID should not be null after saving");
        Assertions.assertEquals("Jane Doe", savedClient.getName(), "Expected name: Jane Doe, but found: " + savedClient.getName());
    }

    @Test
    public void testUpdateById() {
        Client client = new Client();
        client.setName("Michael Smith");
        client.setEmail("michael.smith@example.com");
        client.setPhone("1111111111");
        client.setAddress("456 Birch Road");
        client.setAge(40);
        client.setPremium(true);
        Client savedClient = clientDao.save(client);

        savedClient.setPhone("2222222222");
        savedClient.setAddress("789 Cedar Avenue");
        Client updatedClient = clientDao.save(savedClient);

        Client foundClient = clientDao.findById(updatedClient.getId());
        printClientInfo("Found after update", foundClient);
        Assertions.assertNotNull(foundClient, "Client should be found after update, but was null");
        Assertions.assertEquals("2222222222", foundClient.getPhone(), "Expected phone: 2222222222, but found: " + foundClient.getPhone());
        Assertions.assertEquals("789 Cedar Avenue", foundClient.getAddress(), "Expected address: 789 Cedar Avenue, but found: " + foundClient.getAddress());
    }

    @Test
    public void testDeleteById() {
        //Création du client
        Client client = new Client();
        client.setName("Emily Davis");
        client.setEmail("emily.davis@example.com");
        client.setPhone("3333333333");
        client.setAddress("1010 Spruce Lane");
        client.setAge(33);
        client.setPremium(false);

        //Sauvegarde du client
        Client savedClient = clientDao.save(client);
        Client baseClient = clientDao.findById(savedClient.getId());
        Assertions.assertNotNull(baseClient, "Client should be found after saving, but was null");
        printClientInfo("Created", baseClient);

        //Suppression du client
        clientDao.deleteById(savedClient.getId());
        baseClient = clientDao.findById(savedClient.getId());
        Assertions.assertNull(baseClient, "Client should not be found after deletion, but was found");
        System.out.println("Deleted - Client not found");
    }
}
