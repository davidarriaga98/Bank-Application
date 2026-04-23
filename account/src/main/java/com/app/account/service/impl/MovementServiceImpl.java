package com.app.account.service.impl;

import com.app.account.exceptions.ResourceNotFoundException;
import com.app.account.exceptions.ResourceWithErrorException;
import com.app.account.mappers.MovementMapper;
import com.app.account.model.Account;
import com.app.account.model.Movement;
import com.app.account.model.dto.CreateMovementDto;
import com.app.account.model.dto.MovementDto;
import com.app.account.model.dto.ReportDto;
import com.app.account.repository.AccountRepository;
import com.app.account.repository.MovementRepository;
import com.app.account.service.MovementService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
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
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));

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
        BigDecimal amount = createMovementDto.getInitialBalance();

        if (amount.compareTo(BigDecimal.ZERO) < 0 && balance.compareTo(amount.abs()) < 0) {
            throw new ResourceWithErrorException("Saldo no disponible");
        }

        Movement movement = new Movement();
        movement.setMovementDate(LocalDateTime.now());
        movement.setMovementType(createMovementDto.getAccountType());
        movement.setAmount(amount);

        BigDecimal newBalance;
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            newBalance = balance.add(amount);
        } else {
            newBalance = balance.subtract(amount.abs());
        }
        movement.setBalance(newBalance);
        movement.setAccount(account);

        movementRepository.save(movement);

        return movementMapper.toDto(movement);
    }

    private List<ReportDto> movementsToReportDto(List<Movement> movements) {
        return movements.stream()
                .map(movement -> {
                    ReportDto reportDto = new ReportDto();
                    reportDto.setDate(movement.getMovementDate().toLocalDate());
                    reportDto.setClient(movement.getAccount().getClient());
                    reportDto.setAccountNumber(movement.getAccount().getAccountNumber());
                    reportDto.setAccountType(movement.getAccount().getAccountType());
                    reportDto.setInitialBalance(movement.getAccount().getInitialBalance().doubleValue());
                    reportDto.setStatus(movement.getAccount().getStatus());
                    reportDto.setMovement(movement.getAmount().doubleValue());
                    reportDto.setBalance(movement.getBalance().doubleValue());
                    return reportDto;
                })
                .toList();
    }

    @Override
    public List<ReportDto> getReport(Long clientId, LocalDate date) {
        boolean hasClientId = clientId != null;
        boolean hasDate = date != null;

        if (hasDate) {
            LocalDateTime initialDateTime = date.atStartOfDay();
            LocalDateTime finalDateTime = date.atTime(23, 59, 59);

            List<Movement> movements;
            if (hasClientId) {
                movements = movementRepository.findMovementsByAccount_ClientIdAndMovementDateBetween(clientId, initialDateTime, finalDateTime);
            } else {
                movements = movementRepository.findMovementsByMovementDateBetween(initialDateTime, finalDateTime);
            }
            return movementsToReportDto(movements);
        } else if (hasClientId) {
            List<Movement> movements = movementRepository.findMovementsByAccount_ClientId(clientId);
            return movementsToReportDto(movements);
        }

        List<Movement> allMovements = movementRepository.findAll();
        return movementsToReportDto(allMovements);
    }
}