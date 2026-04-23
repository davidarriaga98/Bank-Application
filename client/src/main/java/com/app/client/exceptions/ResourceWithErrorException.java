package com.app.client.exceptions;

public class ResourceWithErrorException extends RuntimeException {
    public ResourceWithErrorException(String message) {
        super(message);
    }
}
