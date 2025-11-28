package me.strand.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static me.strand.utils.constants.Constants.*;

@Getter
public class ValidationException extends RuntimeException {
    private final transient Map<String, Object> error;
    private final transient HttpStatus status;

    public ValidationException(Map<String, Object> error) {
        super(error.toString());
        this.error = error;
        this.status = HttpStatus.valueOf((Integer) error.get(ERROR_RESPONSE_STATUS_CODE_KEY));
    }
}
