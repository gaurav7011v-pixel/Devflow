package com.devflow.backend.exception;
public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message){
         super(message);
    }
}
