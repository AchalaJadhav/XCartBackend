package com.vat.xcart.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    // Optional: You can add additional constructors for different use cases
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
