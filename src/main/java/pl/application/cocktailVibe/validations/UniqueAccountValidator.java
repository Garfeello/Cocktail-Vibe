package pl.application.cocktailVibe.validations;

import pl.application.cocktailVibe.annotations.UniqueAccount;
import pl.application.cocktailVibe.dto.UserDTO;
import pl.application.cocktailVibe.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueAccountValidator implements ConstraintValidator<UniqueAccount, Object> {

    private final UserRepository userRepository;

    public UniqueAccountValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniqueAccount constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserDTO userDTO = (UserDTO) o;
        return userRepository.findByEmail(userDTO.getEmail()).isEmpty();

    }
}
