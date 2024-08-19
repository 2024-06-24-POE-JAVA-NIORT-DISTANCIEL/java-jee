package com.bigcorp.booking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(message = "L'id doit être nul lors de la création d'une reservation.")
    private Long id;

    @NotNull(message = "La date et l'heure de la réservation ne peuvent pas être nulles.")
    private LocalDateTime reservationDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    @NotNull(message = "Le client ne peut pas être nul.")
    private Client client;

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}