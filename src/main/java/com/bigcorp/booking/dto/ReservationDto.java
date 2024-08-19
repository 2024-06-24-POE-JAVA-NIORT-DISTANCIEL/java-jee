package com.bigcorp.booking.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class ReservationDto {

    @Null(message = "L'id doit être nul lors de la création d'une réservation.")
    private Long id;

    @NotNull(message = "La date et l'heure de la réservation ne peuvent pas être nulles.")
    private LocalDateTime reservationDateTime;

    @NotNull(message = "Le clientId ne peut pas être nul.")
    private Long clientId;

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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
