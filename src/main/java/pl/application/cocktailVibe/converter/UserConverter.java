package pl.application.cocktailVibe.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.User;
import pl.application.cocktailVibe.repository.AlcoholRepository;
import pl.application.cocktailVibe.repository.UserRepository;

@Component
public class UserConverter implements Converter<String, User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User convert(String source) {
        return userRepository.findById(Long.parseLong(source)).orElse(new User());
    }
}
