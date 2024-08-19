package com.bigcorp.booking.correction.service;

import com.bigcorp.booking.correction.dao.ClientDao;
import com.bigcorp.booking.correction.model.Client;
import com.bigcorp.booking.correction.model.ClientDto;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class ClientService {
    @Inject
    private ClientDao clientDao;

    public ClientDto getById(Integer id) {
        return toDto(clientDao.getById(id));
    }

    public ClientDto save(Client client) {
        return toDto(clientDao.save(client));
    }

    public void deleteById(Integer id) {
        clientDao.deleteById(id);
    }

    public ClientDto toDto(Client client) {
        ClientDto clientDto = new ClientDto();

        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setSurname(client.getSurname());
        clientDto.setReservations(client.getReservations());

        return clientDto;
    }
}
