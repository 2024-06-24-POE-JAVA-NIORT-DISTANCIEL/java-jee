package com.bigcorp.booking.service;

import com.bigcorp.booking.dao.ClientDao;
import com.bigcorp.booking.model.Client;
import com.bigcorp.booking.dto.ClientDto;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;


/**
 * Service pour la gestion des clients.
 */
@Stateless
public class ClientService {

    @Inject
    private ClientDao clientDao;

    /**
     * Création d'un client.
     * @param clientDto le client est créer.
     * @return le client sauvegardé en DTO.
     */
    public ClientDto save(ClientDto clientDto) {
        logCall("save", "Début", clientDto);
        Client client = toEntity(clientDto);
        client = clientDao.save(client);
        logCall("save", "Client sauvegardé", client);
        return toDto(client);
    }

    /**
     * Récupère tous les clients en base.
     * @return la liste des clients en DTO
     */
    public List<ClientDto> findAll() {
        List<Client> clients = clientDao.findAll();
        logCall("findAll", "Clients trouvés", clients);
        if (clients.isEmpty()) {
            logCall("findAll", "Aucun client trouvé");
            return null;
        }
        return toDtos(clients);
    }

    /**
     * Trouve un client par son ID.
     * @param id l'identifiant du client
     * @return le client en DTO
     */
    public ClientDto findById(Long id) {
        logCall("findById", "Début", id);
        Client client = clientDao.findById(id);
        if (client == null) {
            logCall("findById", "Client non trouvé", id);
        } else {
            logCall("findById", "Client trouvé", client);
        }
        return toDto(client);
    }

    /**
     * Récupère les clients dont le nom contient une partie donnée.
     * @param name la partie du nom du client
     * @return la liste des clients en DTO
     */
    public List<ClientDto> findByNameLike(String name) {
        logCall("findByNamelike", "Début", name);
        List<Client> clients = clientDao.findByNameLike(name);
        logCall("findByNamelike", "Clients trouvés", clients);
        return toDtos(clients);
    }

    /**
     * Met à jour un client.
     * @param clientDto le client à mettre à jour
     * @return le client mis à jour en DTO
     */
    public ClientDto update(ClientDto clientDto) {
        logCall("update", "Début", clientDto);
        Client client = toEntity(clientDto);
        client = clientDao.save(client);
        logCall("update", "Client mis à jour", client);
        return toDto(client);
    }

    /**
     * Supprime un client par son ID.
     * @param id l'identifiant du client à supprimer
     */
    public boolean deleteById(Long id) {
        logCall("deleteById", "Début", id);
        boolean exists = clientDao.existsById(id); // Vérifie si le client existe
        if (exists) {
            clientDao.deleteById(id);
            logCall("deleteById", "Client supprimé", id);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Convertit un client en DTO.
     * @param client l'entité client
     * @return le DTO correspondant
     */
    private ClientDto toDto(Client client) {
        if (client == null) {
            return null;
        }
        ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setPhone(client.getPhone());
        dto.setAddress(client.getAddress());
        dto.setAge(client.getAge());
        dto.setPremium(client.isPremium());
        return dto;
    }

    /**
     * Convertit un DTO en client.
     * @param dto le DTO
     * @return l'entité client correspondante
     */
    private Client toEntity(ClientDto dto) {
        if (dto == null) {
            return null;
        }
        Client client = new Client();
        client.setId(dto.getId());
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setAddress(dto.getAddress());
        client.setAge(dto.getAge());
        client.setPremium(dto.isPremium());
        return client;
    }

    /**
     * Convertit une liste de clients en liste de DTOs.
     * @param clients la liste des clients
     * @return la liste des DTOs
     */
    private List<ClientDto> toDtos(List<Client> clients) {
        List<ClientDto> dtos = new ArrayList<>();
        for (Client client : clients) {
            dtos.add(toDto(client));
        }
        return dtos;
    }

    /**
     * Méthode utilitaire pour afficher les logs avec le statut.
     * @param methodName le nom de la méthode
     * @param status le statut
     * @param args les arguments
     */
    private void logCall(String methodName, String status, Object... args) {
        System.out.println("Méthode appelée : " + methodName);
        System.out.println("Statut : " + status);
        for (Object arg : args) {
            System.out.println("Argument : " + arg);
        }
    }
}
