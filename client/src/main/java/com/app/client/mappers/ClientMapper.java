package com.app.client.mappers;

import com.app.client.model.Client;
import com.app.client.model.dto.ClientDto;
import com.app.client.model.dto.ClientUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(ClientDto dto);

    ClientDto toDto(Client entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(ClientUpdateDto dto, @MappingTarget Client entity);
}
