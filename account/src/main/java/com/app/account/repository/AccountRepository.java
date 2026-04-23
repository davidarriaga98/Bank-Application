package com.app.account.repository;

import com.app.account.model.Account;
import com.app.account.model.enumeration.AccountType;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByAccountNumber(String accountNumber);

    Optional<Account> findOneByAccountNumberAndAccountType(@NotBlank String accountNumber, @NotBlank AccountType accountType);
}
