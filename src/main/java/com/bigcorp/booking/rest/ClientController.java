package com.bigcorp.booking.rest;

import com.bigcorp.booking.correction.model.Client;
import com.bigcorp.booking.correction.model.ClientDto;
import com.bigcorp.booking.correction.service.ClientService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clients")
@RequestScoped
public class ClientController {
    @Inject
    ClientService clientService;

    private final static String VERIFY_ID = "/{id:[0-9]+}";

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    private Response postNewClient(Client client) {
        if (client.getId() != null) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("C'est à l'application de générer automatiquement les id du Client.")
                .type(MediaType.TEXT_PLAIN)
                .build();
        }
        else {
            return Response.status(Response.Status.OK)
                .entity(clientService.save(client))
                .type(MediaType.APPLICATION_JSON)
                .build();
        }
    }

    @DELETE
    @Path(VERIFY_ID)
    public Response deleteclientById(@PathParam("id") Integer id) {
        ClientDto clientDto = clientService.getById(id);

        if (clientDto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(String.format("Le client avec l'id %d n'a pas été trouvé dans la base", id))
                .type(MediaType.TEXT_PLAIN)
                .build();
        }

        if (clientDto.getReservations().size() > 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Le client possède au moins une reservation. Il est donc impossible de le supprimer.")
                .type(MediaType.TEXT_PLAIN)
                .build();
        }

        clientService.deleteById(id);

        clientDto = clientService.getById(id);

        if (clientDto == null) {
            return Response.status(Response.Status.OK)
                .entity("Le client a bien été supprimé.")
                .type(MediaType.TEXT_PLAIN)
                .build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Il y a eu une erreur lors de la suppression du client.")
                .type(MediaType.TEXT_PLAIN)
                .build();
        }
    }
}