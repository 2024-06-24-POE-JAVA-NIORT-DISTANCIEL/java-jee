package com.bigcorp.booking.rest;

import java.util.List;
import org.jboss.logging.Logger;

import com.bigcorp.booking.correction.model.Reservation;
import com.bigcorp.booking.correction.model.ReservationDto;
import com.bigcorp.booking.correction.service.ReservationService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/bookings")
@RequestScoped
public class ReservationController {
    @Inject
    ReservationService reservationService;

    Logger LOGGER = Logger.getLogger(ReservationController.class);

    private final static String VERIFY_ID = "/{id:[0-9]+}";
    private final static String VERIFY_EMAIL = "^[\\w-.]+@[\\w]{3,}.[\\w]{2,}$";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReservationDto> getAllReservations() {
        List<ReservationDto> reservationsDto = reservationService.getAllReservation();

        return reservationsDto;
    }

    @GET
    @Path(VERIFY_ID)
    @Produces(MediaType.APPLICATION_JSON)
    public ReservationDto getReservationById(@PathParam("id") Integer id) {
        ReservationDto maReservationDto = reservationService.getById(id);

        return maReservationDto;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postNewReservation(Reservation reservation) {
        if (!reservation.getEmail().matches(VERIFY_EMAIL)) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("L'email est invalide")
                .type(MediaType.TEXT_PLAIN)
                .build();
        }

        if (reservation.getId() == null) {
            return Response.status(Response.Status.OK)
                .entity(reservationService.save(reservation))
                .type(MediaType.APPLICATION_JSON)
                .build();
        }
        
        if (reservationService.getById(reservation.getId()) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(String.format("L'identifiant %d n'existe pas dans la base. Si vous voulez modifier une reservation, vous devez indiquer un id d'une reservation déjà existante", reservation.getId()))
                .type(MediaType.TEXT_PLAIN)
                .build();
        }
        else {
            return Response.status(Response.Status.OK)
                .entity(reservationService.save(reservation))
                .type(MediaType.APPLICATION_JSON)
                .build();
        }
    }

    @DELETE
    @Path(VERIFY_ID)
    public Response deleteReservationById(@PathParam("id") Integer id) {
        ReservationDto maReservationDto = reservationService.getById(id);

        if (maReservationDto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(String.format("La reservation avec l'id %d n'a pas été trouvé dans la base", id))
                .type(MediaType.TEXT_PLAIN)
                .build();
        }

        reservationService.deleteById(id);

        maReservationDto = reservationService.getById(id);

        if (maReservationDto == null) {
            return Response.status(Response.Status.OK)
                .entity("La reservation a bien été supprimée.")
                .type(MediaType.TEXT_PLAIN)
                .build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Il y a eu une erreur lors de la suppression de la reservation.")
                .type(MediaType.TEXT_PLAIN)
                .build();
        }
    }
}