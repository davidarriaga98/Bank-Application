package com.app.account.repository;

import com.app.account.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
    Optional<Movement> findTopByAccount_IdOrderByMovementDateDesc(Long accountId);

    List<Movement> findByAccount_Id(Long accountId);
}
