package com.app.account.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MovementDto {
    private Long id;

    @JsonProperty("fecha")
    private LocalDateTime movementDate;

    @JsonProperty("tipoMovimiento")
    private String movementType;

    @JsonProperty("valor")
    private BigDecimal amount;

    @JsonProperty("saldo")
    private BigDecimal balance;
}
