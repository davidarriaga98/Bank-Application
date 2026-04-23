package com.app.client.service.impl;

import com.app.client.exceptions.ResourceExistsException;
import com.app.client.exceptions.ResourceNotFoundException;
import com.app.client.mappers.ClientMapper;
import com.app.client.model.Client;
import com.app.client.model.dto.ClientDto;
import com.app.client.model.dto.ClientUpdateDto;
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
        boolean exists = clientRepository.existsByIdentification(clientDto.getIdentification());
        if (exists) {
            throw new ResourceExistsException("Identificación ya existe");
        }

        Client newClient = clientRepository.save(clientMapper.toEntity(clientDto));
        return clientMapper.toDto(newClient);
    }

    @Override
    public ClientDto update(Long id, ClientUpdateDto clientDto) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("cliente no encontrado"));

        clientMapper.updateFromDto(clientDto, existingClient);
        Client updatedClient = clientRepository.save(existingClient);
        return clientMapper.toDto(updatedClient);
    }

    @Override
    public void delete(Long id) {
        boolean exists = clientRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Cliente no encontrado");
        }
        clientRepository.deleteById(id);
    }
}
