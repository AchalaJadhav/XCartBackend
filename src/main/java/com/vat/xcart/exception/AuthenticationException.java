package com.vat.xcart.exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }

    // Optional: You can add additional constructors for different use cases
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}