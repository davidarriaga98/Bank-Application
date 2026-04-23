package com.app.client.service.impl;

import com.app.client.model.dto.ClientDto;
import com.app.client.service.ClientRabbitMQ;
import com.app.client.service.ClientService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class ClientRabbitMQImpl implements ClientRabbitMQ {
    private final ClientService clientService;
    private final ObjectMapper objectMapper;

    public ClientRabbitMQImpl(ClientService clientService, ObjectMapper objectMapper) {
        this.clientService = clientService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "client.obtain")
    public String handleObtainClient(Long userId) {
        try {
            ClientDto clientDto = clientService.getById(userId);
            return objectMapper.writeValueAsString(clientDto);
        } catch (Exception e) {
            return null;
        }
    }
}
