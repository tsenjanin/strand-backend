package me.strand.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import me.strand.error.ErrorCode;
import me.strand.error.ErrorProperties;
import me.strand.error.ErrorResponseBuilder;
import me.strand.exception.ValidationException;
import me.strand.model.rest.request.InsertCommentRequest;
import me.strand.validation.annotation.ValidComment;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class CommentValidator implements ConstraintValidator<ValidComment, InsertCommentRequest> {
    private final ErrorResponseBuilder errorResponseBuilder;
    private final ErrorProperties errorProperties;

    @Override
    public boolean isValid(InsertCommentRequest request, ConstraintValidatorContext constraintValidatorContext) {
        check(() -> !request.getContent().isEmpty(), ErrorCode.COMMENT_CONTENT_EMPTY);

        return true;
    }

    private void check(Supplier<Boolean> expression, ErrorCode errorCode) {
        if (!expression.get()) {
            throw new ValidationException(errorResponseBuilder.build(errorProperties.getError(errorCode)));
        }
    }
}