package com.app.account.controller;

import com.app.account.model.dto.CreateMovementDto;
import com.app.account.model.dto.MovementDto;
import com.app.account.service.MovementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovementController {
    private final MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @GetMapping
    public ResponseEntity<List<MovementDto>> getAll() {
        return ResponseEntity.ok(movementService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementDto> getById(@PathVariable Long id) {
        MovementDto movementDto = movementService.getById(id);
        if (movementDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movementDto);
    }

    @GetMapping("/cuentas/{accountId}")
    public ResponseEntity<List<MovementDto>> getByAccountId(@PathVariable Long accountId) {
        return ResponseEntity.ok(movementService.getByAccountId(accountId));
    }

    @PostMapping
    public ResponseEntity<MovementDto> create(@Valid @RequestBody CreateMovementDto createMovementDto) {
        return ResponseEntity.ok(movementService.create(createMovementDto));
    }
}
