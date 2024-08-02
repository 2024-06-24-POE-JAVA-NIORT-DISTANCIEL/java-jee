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
    public void testFindAllReservations() {
        printAsciiArt();
        System.out.println("Running testFindAllReservations");
        printSeparator();

        // Create and save a client
        Client client = new Client();
        client.setName("John Doe");
        client.setEmail("john.doe@example.com");
        client.setPhone("0987656521");
        client.setAddress("123 Maple Street");
        client.setAge(28);
        client.setPremium(true);
        Client savedClient = clientDao.save(client);

        // Create and save multiple reservations linked to the client
        Reservation reservation1 = new Reservation();
        reservation1.setReservationDateTime(LocalDateTime.of(2024, 8, 5, 12, 30));
        reservation1.setClient(savedClient);
        reservationDao.save(reservation1);

        Reservation reservation2 = new Reservation();
        reservation2.setReservationDateTime(LocalDateTime.of(2024, 9, 7, 20, 0));
        reservation2.setClient(savedClient);
        reservationDao.save(reservation2);

        // Find all reservations
        List<Reservation> reservations = reservationDao.findAll();
        Assertions.assertFalse(reservations.isEmpty(), "Reservations should be found");
        for (Reservation res : reservations) {
            printSeparator();
            System.out.println("Reservation ID: " + res.getId());
            System.out.println("Reservation DateTime: " + res.getReservationDateTime());
            System.out.println("Client Name: " + res.getClient().getName());
            System.out.println("Client Email: " + res.getClient().getEmail());
            printSeparator();
        }
    }

    @Test
    public void testFindReservationByClient() {
        printAsciiArt();
        System.out.println("Running testFindReservationByClient");
        printSeparator();

        // Create and save a client
        Client client = new Client();
        client.setName("Jane Doe");
        client.setEmail("jane.doe@example.com");
        client.setPhone("0987654321");
        client.setAddress("321 Maple Street");
        client.setAge(28);
        client.setPremium(false);
        Client savedClient = clientDao.save(client);

        // Create and save multiple reservations linked to the client
        Reservation reservation1 = new Reservation();
        reservation1.setReservationDateTime(LocalDateTime.of(2024, 8, 2, 19, 0));
        reservation1.setClient(savedClient);
        reservationDao.save(reservation1);

        Reservation reservation2 = new Reservation();
        reservation2.setReservationDateTime(LocalDateTime.of(2024, 9, 3, 11, 30));
        reservation2.setClient(savedClient);
        reservationDao.save(reservation2);

        // Find reservations by client name
        List<Reservation> reservations = reservationDao.findByClientName("Jane Doe");
        Assertions.assertFalse(reservations.isEmpty(), "Reservations should be found for client name 'Jane Doe'");
        for (Reservation res : reservations) {
            printSeparator();
            System.out.println("Reservation ID: " + res.getId());
            System.out.println("Reservation DateTime: " + res.getReservationDateTime());
            System.out.println("Client Name: " + res.getClient().getName());
            System.out.println("Client Email: " + res.getClient().getEmail());
            printSeparator();
        }
    }
}