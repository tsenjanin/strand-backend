package me.strand.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private String code;
    private String message;
    private HttpStatus httpStatus;
}
