package com.bigcorp.booking.rest;

import com.bigcorp.booking.model.Voiture;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Path("/voiture")
public class VoitureRestController {

    private static Map<Long, Voiture> voitureStore = new HashMap<>();
    // Compteur atomique utilisé pour générer des ids uniques pour les nouvelles vitures.
    // Il assure assure que les operations d'incrementation sont thread-safe.
    private static AtomicLong idCounter = new AtomicLong();

    // Affiche un message lorsque la méthode est appelée
    private void logCall(String methodName, Object... args) {
        System.out.println("Méthode appelée : " + methodName);
        for (Object arg : args) {
            System.out.println("Argument : " + arg);
        }
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        logCall("getById", id);
        Voiture voiture = voitureStore.get(id);
        if (voiture == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(voiture).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Voiture voiture) {
        logCall("create", voiture);
        Long newId = idCounter.incrementAndGet();
        voiture.setId(newId);
        voitureStore.put(newId, voiture);
        return Response.status(Response.Status.CREATED).entity(voiture).build();
    }

    @PUT
    @Path("/{id:[0-9][0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Voiture voiture) {
        logCall("update", id, voiture);
        if (!voitureStore.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        voiture.setId(id);
        voitureStore.put(id, voiture);
        return Response.ok(voiture).build();
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response delete(@PathParam("id") Long id) {
        logCall("delete", id);
        Voiture removedVoiture = voitureStore.remove(id);
        if (removedVoiture == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
