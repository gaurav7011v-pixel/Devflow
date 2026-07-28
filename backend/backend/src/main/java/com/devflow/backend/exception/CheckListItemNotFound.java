package com.devflow.backend.exception;

public class CheckListItemNotFound extends RuntimeException {
    public CheckListItemNotFound(String message) {
        super(message);
    }
}
