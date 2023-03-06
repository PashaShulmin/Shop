package ru.itis.shop.validation.annotations;

import ru.itis.shop.validation.validators.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface NotSimplePassword {
    String message() default "Password in blacklist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}