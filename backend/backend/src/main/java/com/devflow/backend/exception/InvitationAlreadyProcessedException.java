package com.devflow.backend.exception;

public class InvitationAlreadyProcessedException extends RuntimeException {
    public InvitationAlreadyProcessedException(String message) {
        super(message);
    }
}
