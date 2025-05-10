// package com.koushik.firstproject.exception;

// import java.util.Arrays;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.AuthenticationException; // Import the correct class
// import org.springframework.web.bind.annotation.*;

// import java.util.HashMap;
// import java.util.Map;

// @RestControllerAdvice
// public class GlobalExceptionHandler {

//     @ExceptionHandler(AuthenticationException.class)
//     public ResponseEntity<?> handleAuthenticationException(AuthenticationException ex) {
//         if (ex == null) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
//                 "status", false,
//                 "message", "Unknown Authentication Error",
//                 "status_code", 500
//             ));
//         }
    
//         ex.printStackTrace(); // Log stack trace
    
//         String errorMessage = (ex.getMessage() != null && !ex.getMessage().isEmpty())
//             ? ex.getMessage()
//             : ex.getClass().getSimpleName(); // Safe fallback
    
//         Map<String, Object> response = new HashMap<>();
//         response.put("status", false);
//         response.put("message", "Unauthorized access");
//         response.put("status_code", 401);
//         response.put("error", errorMessage);
    
//         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//     }
    
    
//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<?> handleAllExceptions(Exception ex) {
//         if (ex == null) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
//                 "status", false,
//                 "message", "Unknown Server Error",
//                 "status_code", 500
//             ));
//         }
    
//         ex.printStackTrace(); // Optional: log to file in production
//         String errorMessage = (ex.getMessage() != null && !ex.getMessage().isEmpty()) ? ex.getMessage() : "An unexpected error occurred";
//         String errorMessage1 = (ex.getMessage() != null && !ex.getMessage().isEmpty())
//         ? ex.getMessage()
//         : ex.getClass().getSimpleName(); // fallback to exception type

//         Map<String, Object> response = new HashMap<>();
//         response.put("status", false);
//         response.put("message", "Something went wrong");
//         response.put("status_code", 500);
//         response.put("error", errorMessage); 
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
//             "status", false,
//             "message", "Something went wrong",
//             "status_code", 500,
//             "error", errorMessage1
//         ));
//     }
  
// }

package com.koushik.firstproject.exception;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException ex) {
        if (ex == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "status", false,
                "message", "Unknown Authentication Error",
                "status_code", 500
            ));
        }
        
        // Extract error location information
        String errorLocation = extractErrorLocation(ex);
        
        // Enhanced logging with file and line information
        logger.error("Authentication exception at {}: {}", errorLocation, ex.getMessage(), ex);
    
        String errorMessage = (ex.getMessage() != null && !ex.getMessage().isEmpty())
            ? ex.getMessage()
            : ex.getClass().getSimpleName(); // Safe fallback
    
        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("message", "Unauthorized access");
        response.put("status_code", 401);
        response.put("error", errorMessage);
        response.put("location", errorLocation);
    
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
        
        // Extract error location information
        String errorLocation = extractErrorLocation(ex);
        
        // Enhanced logging with file and line information
        logger.error("Exception occurred at {}: {}", errorLocation, ex.getMessage(), ex);
    
        String errorMessage = (ex.getMessage() != null && !ex.getMessage().isEmpty()) 
            ? ex.getMessage() 
            : ex.getClass().getSimpleName(); // fallback to exception type

        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("message", "Something went wrong");
        response.put("status_code", 500);
        response.put("error", errorMessage);
        response.put("location", errorLocation);
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    
    /**
     * Handles Java Error types (like compilation errors)
     */
    @ExceptionHandler(Error.class)
    public ResponseEntity<?> handleJavaErrors(Error error) {
        // Extract error location information
        String errorLocation = extractErrorLocation(error);
        
        // Log with detailed information
        logger.error("Critical error occurred at {}: {}", errorLocation, error.getMessage(), error);
        
        String errorMessage = (error.getMessage() != null && !error.getMessage().isEmpty()) 
            ? error.getMessage() 
            : error.getClass().getSimpleName();
            
        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("message", "Critical system error");
        response.put("status_code", 500);
        response.put("error", errorMessage);
        response.put("location", errorLocation);
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    
    /**
     * Extracts the error location (class, method, file, line) from a Throwable
     */
    private String extractErrorLocation(Throwable throwable) {
        if (throwable == null || throwable.getStackTrace() == null || throwable.getStackTrace().length == 0) {
            return "Unknown location";
        }
        
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        
        // First try to find elements from your application package
        for (StackTraceElement element : stackTrace) {
            if (element.getClassName().startsWith("com.koushik.firstproject")) {
                return formatErrorLocation(element);
            }
        }
        
        // If no application package elements found, return the topmost element
        return formatErrorLocation(stackTrace[0]);
    }
    
    /**
     * Formats a stack trace element into a readable location string
     */
    private String formatErrorLocation(StackTraceElement element) {
        return element.getClassName() + "." + 
               element.getMethodName() + "(" + 
               element.getFileName() + ":" + 
               element.getLineNumber() + ")";
    }
}
