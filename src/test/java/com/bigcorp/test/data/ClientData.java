package com.bigcorp.test.data;

import com.bigcorp.booking.model.Client;
import java.util.ArrayList;
import java.util.List;


public class ClientData {

    public static List<Client> getSampleClients() {
        List<Client> clients = new ArrayList<>();
        clients.add(createClient("Jane Doe", "jane.doe@example.com", "0987654321", "321 Maple Street", 28, false));
        clients.add(createClient("John Doe", "john.doe@example.com", "1234567890", "123 Elm Street", 30, true));
        clients.add(createClient("Alice Smith", "alice.smith@example.com", "2345678901", "456 Oak Avenue", 25, false));
        clients.add(createClient("Bob Johnson", "bob.johnson@example.com", "3456789012", "789 Pine Road", 35, true));
        clients.add(createClient("Charlie Brown", "charlie.brown@example.com", "4567890123", "101 Maple Street", 40, false));
        clients.add(createClient("David Wilson", "david.wilson@example.com", "5678901234", "202 Elm Avenue", 45, true));
        clients.add(createClient("Eve Davis", "eve.davis@example.com", "6789012345", "303 Oak Road", 50, false));
        clients.add(createClient("Frank Miller", "frank.miller@example.com", "7890123456", "404 Pine Avenue", 55, true));
        clients.add(createClient("Grace Lee", "grace.lee@example.com", "8901234567", "505 Maple Road", 60, false));
        clients.add(createClient("Hank Green", "hank.green@example.com", "9012345678", "606 Elm Street", 65, true));
        return clients;
    }

    private static Client createClient(String name, String email, String phone, String address, int age, boolean premium) {
        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setPhone(phone);
        client.setAddress(address);
        client.setAge(age);
        client.setPremium(premium);
        return client;
    }
}