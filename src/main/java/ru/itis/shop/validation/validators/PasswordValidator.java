package ru.itis.shop.validation.validators;

import org.springframework.beans.factory.annotation.Value;
import ru.itis.shop.validation.annotations.NotSimplePassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PasswordValidator implements ConstraintValidator<NotSimplePassword, String> {
    @Value("${files.path}")
    public String path;

    @Override
    public void initialize(NotSimplePassword notSimplePassword) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        try {
            boolean isContains = Files
                    .lines(Paths.get(path))
                    .anyMatch(e -> e.equals(password));
            return !isContains;
        } catch (IOException e) {
            return false;
        }
    }
}
