package com.devflow.backend.exception;

import com.devflow.backend.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
        @ExceptionHandler(EmailAlreadyExistsException.class)
        public ResponseEntity<ErrorResponse> handleEmailAlreadyExists(EmailAlreadyExistsException exception){
            ErrorResponse errorResponse=new ErrorResponse();
            errorResponse.setMessage(exception.getMessage());
            errorResponse.setStatus(HttpStatus.CONFLICT.value());
            errorResponse.setTimeStamp(LocalDateTime.now());

            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }

}

