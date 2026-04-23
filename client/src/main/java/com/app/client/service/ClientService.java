package com.app.client.service;

import com.app.client.model.dto.ClientDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAll();

    ClientDto getById(Long id);

    ClientDto create(ClientDto clientDto);

    ClientDto update(Long id, ClientDto clientDto);

    void delete(Long id);
}
