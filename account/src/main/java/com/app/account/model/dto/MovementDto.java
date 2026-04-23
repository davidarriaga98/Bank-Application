package com.app.account.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MovementDto {
    private Long id;
    private LocalDateTime movementDate;
    private String movementType;
    private BigDecimal amount;
    private BigDecimal balance;
}
