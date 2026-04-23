package com.app.account.exceptions;

public class ResourceWithErrorException extends RuntimeException {
    public ResourceWithErrorException(String message) {
        super(message);
    }
}
