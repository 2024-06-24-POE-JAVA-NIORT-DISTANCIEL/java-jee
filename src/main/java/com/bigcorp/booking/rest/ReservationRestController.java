package com.bigcorp.booking.rest;

import com.bigcorp.booking.model.Reservation;
import com.bigcorp.booking.service.ReservationService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/reservations")
@RequestScoped
public class ReservationRestController {

    @Inject
    private ReservationService reservationService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrUpdateReservation(Reservation reservation) {
        try {
            Reservation savedReservation = reservationService.saveOrUpdate(reservation);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(savedReservation)
                    .build();
        } catch (IllegalArgumentException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservation(@PathParam("id") Long id) {
        try {
            Reservation reservation = reservationService.findById(id);
            return Response
                    .status(Response.Status.OK)
                    .entity(reservation)
                    .build();
        } catch (IllegalArgumentException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }

}
