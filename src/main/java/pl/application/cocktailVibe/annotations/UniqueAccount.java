package pl.application.cocktailVibe.annotations;

import pl.application.cocktailVibe.validations.UniqueAccountValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE,ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueAccountValidator.class)
@Documented
public @interface UniqueAccount {
    String message() default "Account already exsists !";
    Class<?>[] groups() default {}; 
    Class<? extends Payload>[] payload() default {};
}