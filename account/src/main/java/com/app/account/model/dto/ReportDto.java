package com.app.account.model.dto;

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
    private String accountType;

    @JsonProperty("saldoInicial")
    private Double initialBalance;

    @JsonProperty("estado")
    private Boolean status;

    @JsonProperty("movimiento")
    private Double movement;

    @JsonProperty("saldoDisponible")
    private Double balance;
}
