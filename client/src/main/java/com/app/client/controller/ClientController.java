package com.app.client.controller;

import com.app.client.model.dto.ClientDto;
import com.app.client.model.dto.ClientUpdateDto;
import com.app.client.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAll() {
        return ResponseEntity.ok(clientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable Long id) {
        ClientDto clientDto = clientService.getById(id);
        if (clientDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientDto);
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@Valid @RequestBody ClientDto clientDto) {
        ClientDto newClient = clientService.create(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable Long id, @RequestBody ClientUpdateDto clientDto) {
        ClientDto updatedClient = clientService.update(id, clientDto);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
