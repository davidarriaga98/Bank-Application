package com.app.client.controller;

import com.app.client.exceptions.ResourceExistsException;
import com.app.client.exceptions.ResourceNotFoundException;
import com.app.client.exceptions.ResourceWithErrorException;
import com.app.client.model.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientExceptionHandler {
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
