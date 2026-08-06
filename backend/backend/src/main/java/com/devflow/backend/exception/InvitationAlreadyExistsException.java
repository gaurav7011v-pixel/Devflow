package com.devflow.backend.exception;

public class InvitationAlreadyExistsException extends RuntimeException {
    public InvitationAlreadyExistsException(String message) {
        super(message);
    }
}
