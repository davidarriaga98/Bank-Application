package com.app.account.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    @Length(max = 50)
    @NotBlank
    private String accountNumber;

    @Length(max = 50)
    @NotBlank
    private String client;

    @Length(max = 50)
    @NotBlank
    private String accountType;

    @NotNull
    private BigDecimal initialBalance;

    @NotNull
    private Boolean status;
}
