package com.bigcorp.booking.service;

import com.bigcorp.booking.dao.ClientDao;
import com.bigcorp.booking.dao.ReservationDao;
import com.bigcorp.booking.dto.ReservationDto;
import com.bigcorp.booking.model.Client;
import com.bigcorp.booking.model.Reservation;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.Optional;

/**
 * Service pour la gestion des bookings.
 */
@Stateless
public class ReservationService {

    @Inject
    private ReservationDao reservationDao;

    @Inject
    private ClientDao clientDao;

    /**
     * Sauvegarde ou met à jour une réservation.
     * @param reservationDto la réservation à sauvegarder ou mettre à jour
     * @return la réservation sauvegardée
     */
    public ReservationDto saveOrUpdate(ReservationDto reservationDto) {
        // Vérifier si l'id existe déjà dans la base
        if (reservationDto.getId() != null && !reservationDao.existsById(reservationDto.getId())) {
            throw new IllegalArgumentException("Aucune réservation trouvée pour l'identifiant : " + reservationDto.getId());
        }

        // Vérifier si le client existe
        Client client = clientDao.findById(reservationDto.getClientId());
        if (client == null) {
            throw new IllegalArgumentException("Aucun client trouvé pour l'identifiant : " + reservationDto.getClientId());
        }

        // Convertir le DTO en entité
        Reservation reservation = toEntity(reservationDto, client);

        // Sauvegarder la réservation
        reservation = reservationDao.save(reservation);

        // Convertir l'entité en DTO pour la réponse
        return toDto(reservation);
    }

    /**
     * Trouve une réservation par son ID.
     * @param id l'identifiant de la réservation
     * @return la réservation en DTO
     */
    public ReservationDto findById(Long id) {
        Reservation reservation = reservationDao.findById(id);
        if (reservation == null) {
            throw new IllegalArgumentException("Aucune réservation trouvée pour l'identifiant : " + id);
        }
        return toDto(reservation);
    }

    /**
     * Convertit un DTO en entité.
     * @param dto le DTO
     * @param client l'entité client associée
     * @return l'entité réservation correspondante
     */
    private Reservation toEntity(ReservationDto dto, Client client) {
        if (dto == null) {
            return null;
        }
        Reservation reservation = new Reservation();
        reservation.setId(dto.getId());
        reservation.setReservationDateTime(dto.getReservationDateTime());
        reservation.setClient(client);
        return reservation;
    }

    /**
     * Convertit une entité en DTO.
     * @param reservation l'entité réservation
     * @return le DTO correspondant
     */
    private ReservationDto toDto(Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        ReservationDto dto = new ReservationDto();
        dto.setId(reservation.getId());
        dto.setReservationDateTime(reservation.getReservationDateTime());
        dto.setClientId(reservation.getClient().getId()); // Assurez-vous que client n'est pas null
        return dto;
    }

    /**
     * Méthode utilitaire pour afficher les logs avec le statut.
     * @param methodName le nom de la méthode
     * @param status le statut
     * @param args les arguments
     */
    private void logCall(String methodName, String status, Object... args) {
        System.out.println("Méthode appelée : " + methodName);
        System.out.println("Statut : " + status);
        for (Object arg : args) {
            System.out.println("Argument : " + arg);
        }
    }
}
