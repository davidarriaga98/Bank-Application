package com.app.account.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateMovementDto {
    @JsonProperty("numeroCuenta")
    @NotBlank
    private String accountNumber;

    @JsonProperty("tipoCuenta")
    @NotBlank
    private String accountType;

    @JsonProperty("saldoInicial")
    private BigDecimal initialBalance;
}
