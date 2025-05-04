package com.koushik.firstproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException; // Import the correct class
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    
        ex.printStackTrace(); // Log stack trace
    
        String errorMessage = (ex.getMessage() != null && !ex.getMessage().isEmpty())
            ? ex.getMessage()
            : ex.getClass().getSimpleName(); // Safe fallback
    
        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("message", "Unauthorized access");
        response.put("status_code", 401);
        response.put("error", errorMessage);
    
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
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
        String errorMessage = (ex.getMessage() != null && !ex.getMessage().isEmpty()) ? ex.getMessage() : "An unexpected error occurred";
        String errorMessage1 = (ex.getMessage() != null && !ex.getMessage().isEmpty())
        ? ex.getMessage()
        : ex.getClass().getSimpleName(); // fallback to exception type

        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("message", "Something went wrong");
        response.put("status_code", 500);
        response.put("error", errorMessage); 
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
            "status", false,
            "message", "Something went wrong",
            "status_code", 500,
            "error", errorMessage1
        ));
    }
    
}
