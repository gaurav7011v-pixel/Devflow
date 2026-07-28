package com.devflow.backend.exception;

import com.devflow.backend.dto.CommentResponse;
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

        @ExceptionHandler(ProjectNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleProjectNotFound(ProjectNotFoundException exception){
            ErrorResponse errorResponse=new ErrorResponse();
            errorResponse.setMessage(exception.getMessage());
            errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
            errorResponse.setTimeStamp(LocalDateTime.now());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }


    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFound(TaskNotFoundException exception){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimeStamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(NoCommentFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoCommentFound(NoCommentFoundException exception){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimeStamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(CheckListItemNotFound.class)
    public ResponseEntity<ErrorResponse> handleCheckListItemNotFound(CheckListItemNotFound exception){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimeStamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}

