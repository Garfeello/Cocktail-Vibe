package pl.application.cocktailVibe.validations;

import pl.application.cocktailVibe.annotations.PasswordValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidateValidator implements ConstraintValidator<PasswordValidate, String> {
    @Override
    public void initialize(PasswordValidate constraintAnnotation) {
    }
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,24}$");
    }
}
