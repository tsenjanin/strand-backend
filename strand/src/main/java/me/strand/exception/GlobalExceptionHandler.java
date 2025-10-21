package me.strand.exception;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@Hidden
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestControllerException.class)
    public ResponseEntity<Map<String, Object>> handleException(RestControllerException ex) {
        return new ResponseEntity<>(ex.getError(), ex.getStatus());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleException(ValidationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
