package com.app.account.controller;

import com.app.account.exceptions.ResourceExistsException;
import com.app.account.exceptions.ResourceNotFoundException;
import com.app.account.exceptions.ResourceWithErrorException;
import com.app.account.model.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(404).body(
                new ErrorResponseDto(
                        "error",
                        ex.getMessage(),
                        404
                )
        );
    }


    @ExceptionHandler(ResourceExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleExists(ResourceExistsException ex) {
        return ResponseEntity.status(400).body(
                new ErrorResponseDto(
                        "error",
                        ex.getMessage(),
                        400
                )
        );
    }

    @ExceptionHandler(ResourceWithErrorException.class)
    public ResponseEntity<ErrorResponseDto> handleError(ResourceWithErrorException ex) {
        return ResponseEntity.status(400).body(
                new ErrorResponseDto(
                        "error",
                        ex.getMessage(),
                        400
                )
        );
    }
}
