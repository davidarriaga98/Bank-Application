package com.app.account.service.impl;

import com.app.account.mappers.AccountMapper;
import com.app.account.model.Account;
import com.app.account.model.dto.AccountDto;
import com.app.account.model.dto.AccountUpdateDto;
import com.app.account.repository.AccountRepository;
import com.app.account.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<AccountDto> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::toDto)
                .toList();
    }

    @Override
    public AccountDto getById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::toDto)
                .orElse(null);
    }

    @Override
    public AccountDto create(AccountDto accountDto) {
        boolean exists = accountRepository.existsByAccountNumber(accountDto.getAccountNumber());
        if (exists) {
            throw new RuntimeException("Account number already exists");
        }

        Account newAccount = accountRepository.save(accountMapper.toEntity(accountDto));
        return accountMapper.toDto(newAccount);
    }

    @Override
    public AccountDto update(Long id, AccountUpdateDto accountDto) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        accountMapper.updateFromDto(accountDto, existingAccount);
        Account updatedAccount = accountRepository.save(existingAccount);
        return accountMapper.toDto(updatedAccount);
    }

    @Override
    public void delete(Long id) {
        boolean exists = accountRepository.existsById(id);
        if (!exists) {
            throw new RuntimeException("Account not found");
        }
        accountRepository.deleteById(id);
    }
}
