package com.bigcorp.test;

import com.bigcorp.booking.dao.ClientDao;
import com.bigcorp.booking.dao.ReservationDao;
import com.bigcorp.booking.model.Client;
import com.bigcorp.booking.model.Reservation;
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

@ExtendWith(ArquillianExtension.class)
public class ReservationDaoTest {

    @Deployment
    public static WebArchive createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "it.war")
                .addPackages(true, "com.bigcorp.booking")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ReservationDao reservationDao;

    @Inject
    private ClientDao clientDao;

    private void printSeparator() {
        System.out.println("========================================");
    }

    private void printAsciiArt() {
        System.out.println("______________________ ____________________");
        System.out.println("\\__    ___/\\_   _____//   _____/\\__    ___/");
        System.out.println("  |    |    |    __)_ \\_____  \\   |    |   ");
        System.out.println("  |    |    |        \\/        \\  |    |   ");
        System.out.println("  |____|   /_________/_________/  |____|   ");
    }

    @Test
    public void testSaveAndRetrieveReservationWithClient() {
        System.out.println("Running testSaveAndRetrieveReservationWithClient");
        printSeparator();
        printAsciiArt();
        printSeparator();

        // Create and save a client
        Client client = new Client();
        client.setName("John Doe");
        client.setEmail("john.doe@example.com");
        client.setPhone("0987654321");
        client.setAddress("123 Maple Street");
        client.setAge(30);
        client.setPremium(true);
        Client savedClient = clientDao.save(client);

        // Create and save a reservation linked to the client
        Reservation reservation = new Reservation();
        reservation.setReservationDateTime(LocalDateTime.of(2024, 8, 5, 12, 30));
        reservation.setClient(savedClient);
        Reservation savedReservation = reservationDao.save(reservation);

        // Retrieve the reservation and ensure the client's name can be displayed
        Reservation retrievedReservation = reservationDao.findReservationWithClient(savedReservation.getId());
        Assertions.assertNotNull(retrievedReservation);
        Assertions.assertNotNull(retrievedReservation.getClient());
        printSeparator();
        System.out.println("Client Name: " + retrievedReservation.getClient().getName());
        printSeparator();
    }
}