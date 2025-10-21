package me.strand.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.strand.error.ErrorCode;
import me.strand.error.ErrorProperties;
import me.strand.error.ErrorResponseBuilder;
import me.strand.exception.ValidationException;
import me.strand.validation.annotation.ValidUser;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class UserValidator implements ConstraintValidator<ValidUser, Object> {
    private final ErrorResponseBuilder errorResponseBuilder;
    private final ErrorProperties errorProperties;

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        // check(() -> o.equals(), ErrorCode.SOME_ERROR);
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void check(Supplier<Boolean> expression, ErrorCode errorCode) {
        if (!expression.get()) {
            throw new ValidationException(errorResponseBuilder.build(errorProperties.getError(errorCode)));
        }
    }
}
