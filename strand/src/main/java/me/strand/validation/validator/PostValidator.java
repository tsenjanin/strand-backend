package me.strand.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import me.strand.error.ErrorCode;
import me.strand.error.ErrorProperties;
import me.strand.error.ErrorResponseBuilder;
import me.strand.exception.ValidationException;
import me.strand.model.rest.request.InsertPostRequest;
import me.strand.validation.annotation.ValidPost;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class PostValidator implements ConstraintValidator<ValidPost, InsertPostRequest> {
    private final ErrorResponseBuilder errorResponseBuilder;
    private final ErrorProperties errorProperties;

    @Override
    public boolean isValid(InsertPostRequest request, ConstraintValidatorContext constraintValidatorContext) {
        check(() -> !request.getTitle().isEmpty(), ErrorCode.POST_TITLE_EMPTY);
        check(() -> !request.getContent().isEmpty(), ErrorCode.POST_CONTENT_EMPTY);

        return true;
    }

    private void check(Supplier<Boolean> expression, ErrorCode errorCode) {
        if (!expression.get()) {
            throw new ValidationException(errorResponseBuilder.build(errorProperties.getError(errorCode)));
        }
    }
}
