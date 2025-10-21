package me.strand.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class RestControllerException extends RuntimeException {
    private final transient Map<String, Object> error;
    private final transient HttpStatus status;

    public RestControllerException(Map<String, Object> error) {
        this.error = error;
        this.status = HttpStatus.valueOf((int) error.get("status"));
    }
}