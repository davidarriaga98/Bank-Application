package com.app.account;

import com.app.account.controller.AccountController;
import com.app.account.model.dto.AccountDto;
import com.app.account.model.dto.CreateAccountDto;
import com.app.account.model.enumeration.AccountType;
import com.app.account.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountApplicationTests {
    private final AccountService accountService = mock(AccountService.class);
    private final AccountController accountController = new AccountController(accountService);

    @Test
    void contextLoads() {
    }


    @Test
    void saveAccountTest() {
        CreateAccountDto newAccount = new CreateAccountDto(
                "12345678",
                1L,
                AccountType.CHECKING,
                BigDecimal.valueOf(2000.0)
        );
        AccountDto savedAccount = new AccountDto(
                1L,
                "12345678",
                "David",
                1L,
                AccountType.CHECKING,
                BigDecimal.valueOf(2000.0),
                true
        );
        when(accountService.create(newAccount)).thenReturn(savedAccount);

        ResponseEntity<AccountDto> response = accountController.create(newAccount);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedAccount, response.getBody());
    }

    @Test
    void getAccountByIdTest() {
        Long accountId = 1L;
        AccountDto account = new AccountDto(
                1L,
                "12345678",
                "David",
                1L,
                AccountType.CHECKING,
                BigDecimal.valueOf(2000.0),
                true
        );

        when(accountService.getById(accountId)).thenReturn(account);
        ResponseEntity<AccountDto> response = accountController.getById(accountId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(account, response.getBody());
    }
}
