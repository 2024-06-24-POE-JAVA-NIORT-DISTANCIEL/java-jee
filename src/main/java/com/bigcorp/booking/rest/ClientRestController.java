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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(ClientDto clientDto) {
        if (clientDto == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Le corps de la requête est vide.").build();
        }
        try {
            ClientDto savedClient = clientService.save(clientDto);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Client créé avec succès.");
            response.put("client", savedClient);
            return Response.status(Response.Status.CREATED).entity(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la sauvegarde du client.").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        try {
            List<ClientDto> clients = clientService.findAll();
            if (clients.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).entity("Aucun client trouvé.").build();
            }
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Clients récupérés avec succès.");
            response.put("clients", clients);
            return Response.ok(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la récupération des clients.").build();
        }
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        try {
            ClientDto clientDto = clientService.findById(id);
            if (clientDto == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Client non trouvé pour l'id : " + id).build();
            }
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Client récupéré avec succès.");
            response.put("client", clientDto);
            return Response.ok(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la récupération du client avec l'id : " + id).build();
        }
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByName(@QueryParam("name") String name) {
        if (name == null || name.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Le paramètre 'name' est requis.").build();
        }
        try {
            List<ClientDto> clients = clientService.findByNameLike(name);
            if (clients.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).entity("Aucun client trouvé pour le nom : " + name).build();
            }
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Clients trouvés avec succès.");
            response.put("clients", clients);
            return Response.ok(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la recherche de clients avec le nom : " + name).build();
        }
    }

    @PUT
    @Path("/{id:[0-9][0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, ClientDto clientDto) {
        if (clientDto == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Le corps de la requête est vide.").build();
        }
        if (!id.equals(clientDto.getId())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("L'id du client ne correspond pas à l'id de l'URL.").build();
        }
        try {
            ClientDto updatedClient = clientService.save(clientDto);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Client mis à jour avec succès.");
            response.put("client", updatedClient);
            return Response.ok(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la mise à jour du client.").build();
        }
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Long id) {
        try {
            ClientDto clientDto = clientService.findById(id);
            String clientDtoName = clientDto.getName();
            boolean deleted = clientService.deleteById(id); // Assurez-vous que deleteById renvoie un boolean indiquant le succès de la suppression
            if (deleted)
            {
                String message = "Client id: " + id + ", " + clientDtoName + " supprimé avec succès.";
                Map<String, Object> response = new HashMap<>();
                response.put("message", message);
                return Response.status(Response.Status.OK).entity(response).build();
            } else {
                String message = "Client avec l'id " + id + " n'existe pas.";
                Map<String, Object> response = new HashMap<>();
                response.put("message", message);
                return Response.status(Response.Status.NOT_FOUND).entity(response).build();
            }
        } catch (Exception e) {
            String message = "Erreur lors de la suppression du client avec l'id " + id + ".";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
        }
    }
}