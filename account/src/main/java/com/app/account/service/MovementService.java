package com.app.account.service;

import com.app.account.model.dto.CreateMovementDto;
import com.app.account.model.dto.MovementDto;
import com.app.account.model.dto.ReportDto;

import java.time.LocalDate;
import java.util.List;

public interface MovementService {
    List<MovementDto> getAll();

    MovementDto getById(Long id);

    List<MovementDto> getByAccountId(Long accountId);

    MovementDto create(CreateMovementDto createMovementDto);

    List<ReportDto> getReport(Long clientId, LocalDate date);
}
