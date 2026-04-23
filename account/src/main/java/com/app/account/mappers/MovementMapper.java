package com.app.account.mappers;

import com.app.account.model.Movement;
import com.app.account.model.dto.MovementDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovementMapper {
    Movement toEntity(MovementDto dto);

    MovementDto toDto(Movement entity);
}
