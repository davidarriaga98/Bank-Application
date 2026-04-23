package com.app.account.controller;

import com.app.account.model.dto.ReportDto;
import com.app.account.service.MovementService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReportController {
    private final MovementService movementService;

    public ReportController(MovementService movementService) {
        this.movementService = movementService;
    }

    @GetMapping
    public ResponseEntity<List<ReportDto>> report(@RequestParam(name = "cliente", required = false) Long clientId,
                                                  @RequestParam(name = "fecha", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return ResponseEntity.ok(movementService.getReport(clientId, date));
    }
}
