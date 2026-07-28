package com.devflow.backend.exception;

public class LabelNotFoundException extends RuntimeException {
    public LabelNotFoundException(String message) {
        super(message);
    }
}
