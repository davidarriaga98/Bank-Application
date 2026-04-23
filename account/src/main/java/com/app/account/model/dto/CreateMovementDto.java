package com.app.account.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateMovementDto {
    @NotBlank
    private String accountNumber;
    @NotBlank
    private String accountType;
    private BigDecimal initialBalance;
}
