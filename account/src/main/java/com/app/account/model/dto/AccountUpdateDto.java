package com.app.account.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountUpdateDto {
    @JsonProperty("numeroCuenta")
    private String accountNumber;

    @JsonProperty("tipoCuenta")
    private String accountType;

    @JsonProperty("saldoInicial")
    private BigDecimal initialBalance;

    @JsonProperty("cliente")
    private String client;

    @JsonProperty("estado")
    private Boolean status;
}
