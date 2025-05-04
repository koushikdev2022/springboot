package com.koushik.firstproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException; // Import the correct class
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException ex) {
        if (ex == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "status", false,
                "message", "Unknown Authentication Error",
                "status_code", 500
            ));
        }
    
        ex.printStackTrace(); // Optional: log to file in production
    
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
            "status", false,
            "message", "Unauthorized access",
            "status_code", 401,
            "error", ex.getMessage() != null ? ex.getMessage() : "No message"
        ));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex) {
        if (ex == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "status", false,
                "message", "Unknown Server Error",
                "status_code", 500
            ));
        }
    
        ex.printStackTrace(); // Optional: log to file in production
    
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
            "status", false,
            "message", "Something went wrong",
            "status_code", 500,
            "error", ex.getMessage() != null ? ex.getMessage() : "No message"
        ));
    }
    
}
