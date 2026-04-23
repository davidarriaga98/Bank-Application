package com.app.account.model.dto;

import lombok.Data;

@Data
public class AccountUpdateDto {
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private String client;
    private Boolean status;
}
