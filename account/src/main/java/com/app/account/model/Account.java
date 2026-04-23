package com.app.account.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String accountType;
    private String client;
    private BigDecimal initialBalance;
    private Boolean status;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Movement> movements;
}
