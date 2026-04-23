package com.app.account.service;

import com.app.account.model.dto.AccountDto;
import com.app.account.model.dto.AccountUpdateDto;
import com.app.account.model.dto.CreateAccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAll();

    AccountDto getById(Long id);

    AccountDto create(CreateAccountDto accountDto);

    AccountDto update(Long id, AccountUpdateDto accountDto);

    void delete(Long id);
}
