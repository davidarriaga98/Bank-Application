package com.app.client.service.impl;

import com.app.client.mappers.ClientMapper;
import com.app.client.model.Client;
import com.app.client.model.dto.ClientDto;
import com.app.client.repository.ClientRepository;
import com.app.client.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientDto> getAll() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .toList();
    }

    @Override
    public ClientDto getById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDto)
                .orElse(null);
    }

    @Override
    public ClientDto create(ClientDto clientDto) {
        Client newClient = clientRepository.save(clientMapper.toEntity(clientDto));
        return clientMapper.toDto(newClient);
    }

    @Override
    public ClientDto update(Long id, ClientDto clientDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
