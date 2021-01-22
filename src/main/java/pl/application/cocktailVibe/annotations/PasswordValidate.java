package pl.application.cocktailVibe.annotations;

import pl.application.cocktailVibe.validations.PasswordMatchesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordValidate {
    String message() default "Password msut be between 8-16 characters " +
            "and must contain and least one number and capital letter !";
    Class<?>[] groups() default {}; 
    Class<? extends Payload>[] payload() default {};
}