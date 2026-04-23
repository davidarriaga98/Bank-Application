package com.app.client;

import com.app.client.controller.ClientController;
import com.app.client.model.dto.ClientDto;
import com.app.client.model.dto.ClientUpdateDto;
import com.app.client.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientApplicationTests {
    private final ClientService clientService = mock(ClientService.class);
    private final ClientController clientController = new ClientController(clientService);

    @Test
    void contextLoads() {
    }

    @Test
    void saveClientTest() {
        ClientDto newClient = new ClientDto(
                1L,
                "David",
                "Male",
                (short) 27,
                "099999999",
                "Quito",
                "099999999",
                "password",
                true
        );
        ClientDto savedClient = new ClientDto(
                1L,
                "David",
                "Male",
                (short) 27,
                "099999999",
                "Quito",
                "099999999",
                "password",
                true
        );
        when(clientService.create(newClient)).thenReturn(savedClient);

        ResponseEntity<ClientDto> response = clientController.create(newClient);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedClient, response.getBody());
    }

    @Test
    void updateClientTest() {
        Long clientId = 1L;

        ClientUpdateDto updateDto = new ClientUpdateDto(
                "David Updated",
                "Male",
                (short) 28,
                "088888888",
                "Guayaquil",
                "088888888",
                "newpassword",
                true
        );

        ClientDto updatedClient = new ClientDto(
                clientId,
                "David Updated",
                "Male",
                (short) 28,
                "088888888",
                "Guayaquil",
                "088888888",
                "newpassword",
                true
        );

        when(clientService.update(clientId, updateDto)).thenReturn(updatedClient);

        ResponseEntity<ClientDto> response = clientController.update(clientId, updateDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedClient, response.getBody());
    }

    @Test
    void getClientByIdTest() {
        Long clientId = 1L;
        ClientDto client = new ClientDto(
                1L,
                "David",
                "Male",
                (short) 27,
                "099999999",
                "Quito",
                "099999999",
                "password",
                true
        );

        when(clientService.getById(clientId)).thenReturn(client);
        ResponseEntity<ClientDto> response = clientController.getById(clientId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(client, response.getBody());
    }
}
