package com.app.account.service.impl;

import com.app.account.mappers.MovementMapper;
import com.app.account.model.Account;
import com.app.account.model.Movement;
import com.app.account.model.dto.CreateMovementDto;
import com.app.account.model.dto.MovementDto;
import com.app.account.repository.AccountRepository;
import com.app.account.repository.MovementRepository;
import com.app.account.service.MovementService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovementServiceImpl implements MovementService {
    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;
    private final MovementMapper movementMapper;

    public MovementServiceImpl(MovementRepository movementRepository, AccountRepository accountRepository, MovementMapper movementMapper) {
        this.movementRepository = movementRepository;
        this.accountRepository = accountRepository;
        this.movementMapper = movementMapper;
    }


    @Override
    public List<MovementDto> getAll() {
        return movementRepository.findAll()
                .stream()
                .map(movementMapper::toDto)
                .toList();
    }

    @Override
    public MovementDto getById(Long id) {
        return movementRepository.findById(id)
                .map(movementMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<MovementDto> getByAccountId(Long accountId) {
        return movementRepository.findByAccount_Id(accountId)
                .stream()
                .map(movementMapper::toDto)
                .toList();
    }

    @Override
    public MovementDto create(CreateMovementDto createMovementDto) {
        Account account = accountRepository.findOneByAccountNumberAndAccountType(createMovementDto.getAccountNumber(), createMovementDto.getAccountType())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Optional<Movement> lastMovement = movementRepository.findTopByAccount_IdOrderByMovementDateDesc(account.getId());
        if (lastMovement.isEmpty()) {
            BigDecimal balance = account.getInitialBalance();
            return registerMovement(createMovementDto, account, balance);
        } else {
            Movement last = lastMovement.get();
            BigDecimal balance = last.getBalance();

            return registerMovement(createMovementDto, account, balance);
        }
    }

    private MovementDto registerMovement(CreateMovementDto createMovementDto, Account account, BigDecimal balance) {
        if (balance.compareTo(createMovementDto.getInitialBalance()) < 0) {
            throw new RuntimeException("Saldo no disponible");
        }

        Movement movement = new Movement();
        movement.setMovementDate(LocalDateTime.now());
        movement.setMovementType(createMovementDto.getAccountType());
        movement.setAmount(createMovementDto.getInitialBalance());
        movement.setBalance(balance.subtract(createMovementDto.getInitialBalance()));
        movement.setAccount(account);

        movementRepository.save(movement);

        return movementMapper.toDto(movement);
    }
}
