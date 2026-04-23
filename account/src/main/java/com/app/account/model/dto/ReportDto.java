package com.app.account.model.dto;

import com.app.account.model.enumeration.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportDto {
    @JsonProperty("fecha")
    private LocalDate date;

    @JsonProperty("cliente")
    private String client;

    @JsonProperty("numeroCuenta")
    private String accountNumber;

    @JsonProperty("tipo")
    private AccountType accountType;

    @JsonProperty("saldoInicial")
    private Double initialBalance;

    @JsonProperty("estado")
    private Boolean status;

    @JsonProperty("movimiento")
    private Double movement;

    @JsonProperty("saldoDisponible")
    private Double balance;
}
