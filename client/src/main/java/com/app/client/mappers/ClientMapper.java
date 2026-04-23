package com.app.client.mappers;

import com.app.client.model.Client;
import com.app.client.model.dto.ClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(ClientDto dto);

    ClientDto toDto(Client entity);
}
