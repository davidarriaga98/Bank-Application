package com.app.client.service.impl;

import com.app.client.model.dto.ClientDto;
import com.app.client.repository.ClientRepository;
import com.app.client.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDto> getAll() {
        return List.of();
    }

    @Override
    public ClientDto getById(Long id) {
        return null;
    }

    @Override
    public ClientDto create(ClientDto clientDto) {
        return null;
    }

    @Override
    public ClientDto update(Long id, ClientDto clientDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
