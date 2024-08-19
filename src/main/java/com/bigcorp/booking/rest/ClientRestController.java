package com.bigcorp.booking.rest;

import com.bigcorp.booking.service.ClientService;
import com.bigcorp.booking.dto.ClientDto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/clients")
@RequestScoped
public class ClientRestController {

    @Inject
    private ClientService clientService;

    // Création d'un nouveau client
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(ClientDto clientDto) {
        if (clientDto == null) {
            // Si le corps de la requête est vide, renvoie un statut 400 (Bad Request)
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("X-Error-Type", "Empty Request Body")
                    .entity("Le corps de la requête est vide.")
                    .build();
        }
        try {
            ClientDto savedClient = clientService.save(clientDto);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Client créé avec succès.");
            response.put("client", savedClient);
            // Si le client est créé avec succès, renvoie un statut 201 (Created)
            return Response
                    .status(Response.Status.CREATED)
                    .header("X-Operation-Status", "Created")
                    .entity(response)
                    .build();
        } catch (Exception e) {
            // Si une erreur survient, renvoie un statut 500 (Internal Server Error)
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("X-Error-Type", "Internal Error")
                    .entity("Erreur lors de la sauvegarde du client.")
                    .build();
        }
    }

    // Récupération de la liste de tous les clients
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        try {
            List<ClientDto> clients = clientService.findAll();
            if (clients == null || clients.isEmpty()) {
                // Si aucun client n'est trouvé, renvoie un statut 404 (Not Found)
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .header("X-Error-Type", "No Clients Found")
                        .entity("Aucun client n'a été trouvé.")
                        .build();
            }
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Clients récupérés avec succès.");
            response.put("clients", clients);
            // Si des clients sont trouvés, renvoie un statut 200 (OK) avec les clients en JSON
            return Response
                    .status(Response.Status.OK)
                    .header("X-Operation-Status", "Success")
                    .header("X-Total-Clients", clients.size())
                    .entity(response)
                    .build();
        } catch (Exception e) {
            // Si une erreur survient, renvoie un statut 500 (Internal Server Error)
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("X-Error-Type", "Internal Error")
                    .entity("Erreur lors de la récupération des clients.")
                    .build();
        }
    }

    // Récupération d'un client par son ID
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        try {
            ClientDto clientDto = clientService.findById(id);
            if (clientDto == null) {
                // Si le client avec l'ID donné n'est pas trouvé, renvoie un statut 404 (Not Found)
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .header("X-Error-Type", "Client Not Found")
                        .entity("Client non trouvé pour l'id : " + id)
                        .build();
            }
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Client récupéré avec succès.");
            response.put("client", clientDto);
            // Si le client est trouvé, renvoie un statut 200 (OK) avec les détails du client
            return Response
                    .status(Response.Status.OK)
                    .header("X-Operation-Status", "Success")
                    .entity(response)
                    .build();
        } catch (Exception e) {
            // Si une erreur survient, renvoie un statut 500 (Internal Server Error)
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("X-Error-Type", "Internal Error")
                    .entity("Erreur lors de la récupération du client avec l'id : " + id)
                    .build();
        }
    }

    // Recherche de clients par nom
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByName(@QueryParam("name") String name) {
        if (name == null || name.trim().isEmpty()) {
            // Si le paramètre 'name' est manquant ou vide, renvoie un statut 400 (Bad Request)
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("X-Error-Type", "Invalid Parameter")
                    .entity("Il faut au moins 3 charactères en paramètres `name` pour rechercher un client.")
                    .build();
        }
        try {
            List<ClientDto> clients = clientService.findByNameLike(name);
            if (clients.isEmpty()) {
                // Si aucun client n'est trouvé pour le nom donné, renvoie un statut 204 (No Content)
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .header("X-Error-Type", "Internal Error")
                        .entity("Aucun client n'a été trouvé avec la recherche : " + name)
                        .build();
            }
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Clients trouvés avec succès.");
            response.put("clients", clients);
            // Si des clients sont trouvés, renvoie un statut 200 (OK) avec les clients en JSON
            return Response
                    .status(Response.Status.OK)
                    .header("X-Operation-Status", "Success")
                    .header("X-Total-Clients", clients.size())
                    .entity(response)
                    .build();
        } catch (Exception e) {
            // Si une erreur survient, renvoie un statut 500 (Internal Server Error)
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("X-Error-Type", "Internal Error")
                    .entity("Erreur lors de la recherche de clients avec le nom : " + name)
                    .build();
        }
    }

    // Mise à jour d'un client existant
    @PUT
    @Path("/{id:[0-9][0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, ClientDto clientDto) {
        if (clientDto == null) {
            // Si le corps de la requête est vide, renvoie un statut 400 (Bad Request)
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("X-Error-Type", "Empty Request Body")
                    .entity("Le corps de la requête est vide.")
                    .build();
        }
        if (id == null) {
            // Si l'ID est manquant, renvoie un statut 404 (Not Found)
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .header("X-Error-Type", "Client Not Found")
                    .entity("Il faut un ID pour mettre à jour un client.")
                    .build();
        }
        if (!id.equals(clientDto.getId())) {
            // Si l'ID dans l'URL ne correspond pas à l'ID du client, renvoie un statut 400 (Bad Request)
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("X-Error-Type", "ID Mismatch")
                    .entity("L'id du client ne correspond pas à l'id de l'URL.")
                    .build();
        }
        try {
            ClientDto updatedClient = clientService.save(clientDto);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Client mis à jour avec succès.");
            response.put("client", updatedClient);
            // Si la mise à jour réussit, renvoie un statut 200 (OK)
            return Response
                    .status(Response.Status.OK)
                    .header("X-Operation-Status", "Success")
                    .entity(response)
                    .build();
        } catch (Exception e) {
            // Si une erreur survient, renvoie un statut 500 (Internal Server Error)
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("X-Error-Type", "Internal Error")
                    .entity("Erreur lors de la mise à jour du client.")
                    .build();
        }
    }

    // Suppression d'un client par son ID
    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Long id) {
        try {
            ClientDto clientDto = clientService.findById(id);
            if (clientDto == null) {
                // Si le client n'existe pas, renvoie un statut 404 (Not Found)
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .header("X-Error-Type", "Client Not Found")
                        .entity("Client avec l'id " + id + " n'existe pas.")
                        .build();
            }
            boolean deleted = clientService.deleteById(id);
            if (deleted) {
                // Si la suppression est réussie, renvoie un statut 200 (OK) avec un message de confirmation
                String message = "Client id: " + id + ", " + clientDto.getNom() + " supprimé avec succès.";
                Map<String, Object> response = new HashMap<>();
                response.put("message", message);
                return Response
                        .status(Response.Status.OK)
                        .header("X-Operation-Status", "Deleted")
                        .entity(response)
                        .build();
            } else {
                // Si la suppression échoue, renvoie un statut 404 (Not Found)
                String message = "Client avec l'id " + id + " n'existe pas.";
                Map<String, Object> response = new HashMap<>();
                response.put("message", message);
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .header("X-Error-Type", "Client Not Found")
                        .entity(response)
                        .build();
            }
        } catch (Exception e) {
            // Si une erreur survient, renvoie un statut 500 (Internal Server Error)
            String message = "Erreur lors de la suppression du client avec l'id " + id + ".";
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("X-Error-Type", "Internal Error")
                    .entity(message)
                    .build();
        }
    }
}