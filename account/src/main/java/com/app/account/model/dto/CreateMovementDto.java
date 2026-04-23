package com.app.account.model.dto;

import com.app.account.model.enumeration.AccountType;
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
    private AccountType accountType;

    @JsonProperty("saldoInicial")
    private BigDecimal initialBalance;
}
