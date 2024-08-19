package com.bigcorp.booking.correction.service;

import java.util.ArrayList;
import java.util.List;

import com.bigcorp.booking.correction.dao.ReservationDao;
import com.bigcorp.booking.correction.model.Reservation;
import com.bigcorp.booking.correction.model.ReservationDto;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class ReservationService {
    @Inject
    private ReservationDao reservationDao;

    public ReservationDto getById(Integer id) {
        Reservation reservation = reservationDao.getBydId(id);

        if (reservation == null) {
            return null;
        }

        return toDto(reservation);
    }

    public List<ReservationDto> getAllReservation() {
        List<Reservation> reservations = reservationDao.getAll();

        return toDtoList(reservations);
    }

    public ReservationDto save(Reservation reservation) {
        return toDto(reservationDao.save(reservation));
    }

    public void deleteById(Integer id) {
        reservationDao.deleteById(id);
    }

    public ReservationDto toDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();

        reservationDto.setId(reservation.getId());
        reservationDto.setEmail(reservation.getEmail());
        reservationDto.setClient(reservation.getClient());

        return reservationDto;
    }

    public List<ReservationDto> toDtoList(List<Reservation> reservations) {
        List<ReservationDto> reservationsDto = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationsDto.add(toDto(reservation));
        }

        return reservationsDto;
    }
}
