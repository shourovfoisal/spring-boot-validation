package com.shourov.validation_example.advice;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.shourov.validation_example.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleInvalidMethodArgument(MethodArgumentNotValidException e) {
        Map<String, Object> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, Object> handleNonExistentUser(UserNotFoundException e) {
        Map<String, Object> error = new HashMap<>();
        error.put("message", e.getMessage());
        return error;
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleMalformedRequestBody(HttpMessageNotReadableException e) {
        Map<String, Object> response = new HashMap<>();
        
        Throwable rootCause = e.getMostSpecificCause();
        if(rootCause instanceof JsonMappingException jsonMappingException) {
            List<JsonMappingException.Reference> path = jsonMappingException.getPath();
            if(!path.isEmpty()) {
                String fieldName = path.getFirst().getFieldName();
                response.put("reason", "Malformed data");
                response.put("field", fieldName);
            }
        }
        response.put("details", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
