package com.bigcorp.booking.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class ReservationDto {

    @Null(message = "L'id doit être nul lors de la création d'une réservation.")
    private Long id;

    @NotNull(message = "La date et l'heure de la réservation ne peuvent pas être nulles.")
    private LocalDateTime reservationDateTime;

    @NotNull(message = "Le client ne peut pas être nul.")
    private ClientDto client;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(LocalDateTime reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }
}
