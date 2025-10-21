package me.strand.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import me.strand.validation.validator.UserValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUser {
    String message() default "Invalid Format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
