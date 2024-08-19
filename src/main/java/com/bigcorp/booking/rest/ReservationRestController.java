package com.bigcorp.booking.rest;

import com.bigcorp.booking.dto.ReservationDto;
import com.bigcorp.booking.service.ReservationService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

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
    public Response createOrUpdateReservation(ReservationDto reservationDto) {
        try {
            ReservationDto savedReservationDto = reservationService.saveOrUpdate(reservationDto);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(savedReservationDto)
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
            ReservationDto reservationDto = reservationService.findById(id);
            return Response
                    .status(Response.Status.OK)
                    .entity(reservationDto)
                    .build();
        } catch (IllegalArgumentException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
