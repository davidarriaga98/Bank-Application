package com.app.account.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportDto {
    private LocalDate date;
    private String client;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private Boolean status;
    private Double movement;
    private Double balance;
}
