package com.app.account.service.impl;

import com.app.account.exceptions.ResourceExistsException;
import com.app.account.exceptions.ResourceNotFoundException;
import com.app.account.mappers.AccountMapper;
import com.app.account.model.Account;
import com.app.account.model.dto.AccountDto;
import com.app.account.model.dto.AccountUpdateDto;
import com.app.account.model.dto.ClientMicroserviceResponseDto;
import com.app.account.model.dto.CreateAccountDto;
import com.app.account.repository.AccountRepository;
import com.app.account.service.AccountService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final RabbitTemplate rabbitMqTemplate;
    private final ObjectMapper objectMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, RabbitTemplate rabbitMqTemplate, ObjectMapper objectMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.rabbitMqTemplate = rabbitMqTemplate;
        this.objectMapper = objectMapper;
    }

    private ClientMicroserviceResponseDto obtainClientFromMicroservice(Long clientId) {
        String response = (String) rabbitMqTemplate.convertSendAndReceive(
                "client.obtain",
                clientId
        );
        if (response == null) {
            return null;
        }

        try {
            return objectMapper.readValue(response, ClientMicroserviceResponseDto.class);
        } catch (Exception e) {
            return null;
        }
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
    public AccountDto create(CreateAccountDto accountDto) {
        boolean exists = accountRepository.existsByAccountNumber(accountDto.getAccountNumber());
        if (exists) {
            throw new ResourceExistsException("Cuenta ya existe");
        }

        ClientMicroserviceResponseDto client = obtainClientFromMicroservice(accountDto.getClientId());
        if (client == null) {
            throw new ResourceNotFoundException("Cliente no encontrado");
        }

        Account newAccount = accountMapper.toEntity(accountDto);
        newAccount.setClientId(client.getId());
        newAccount.setClient(client.getName());
        newAccount.setStatus(Boolean.TRUE);

        accountRepository.save(newAccount);

        return accountMapper.toDto(newAccount);
    }

    @Override
    public AccountDto update(Long id, AccountUpdateDto accountDto) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));

        accountMapper.updateFromDto(accountDto, existingAccount);
        Account updatedAccount = accountRepository.save(existingAccount);
        return accountMapper.toDto(updatedAccount);
    }

    @Override
    public void delete(Long id) {
        boolean exists = accountRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Cuenta no encontrada");
        }
        accountRepository.deleteById(id);
    }
}
