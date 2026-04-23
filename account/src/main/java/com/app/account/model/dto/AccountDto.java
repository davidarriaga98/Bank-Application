package com.app.account.model.dto;

import com.app.account.model.enumeration.AccountType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;

    @JsonProperty("numeroCuenta")
    @Length(max = 50)
    @NotBlank
    private String accountNumber;

    @JsonProperty("cliente")
    @Length(max = 50)
    @NotBlank
    private String client;

    @JsonProperty("clienteId")
    @NotNull
    private Long clientId;

    @JsonProperty("tipoCuenta")
    @Length(max = 50)
    @NotBlank
    private AccountType accountType;

    @JsonProperty("saldoInicial")
    @NotNull
    private BigDecimal initialBalance;

    @JsonProperty("estado")
    @NotNull
    private Boolean status;
}
