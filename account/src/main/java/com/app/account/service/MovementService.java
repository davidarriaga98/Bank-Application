package com.app.account.service;

import com.app.account.model.dto.CreateMovementDto;
import com.app.account.model.dto.MovementDto;

import java.util.List;

public interface MovementService {
    List<MovementDto> getAll();

    MovementDto getById(Long id);

    List<MovementDto> getByAccountId(Long accountId);

    MovementDto create(CreateMovementDto createMovementDto);
}
