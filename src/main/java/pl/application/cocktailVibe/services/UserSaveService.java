package pl.application.cocktailVibe.services;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.application.cocktailVibe.dto.UserDTO;
import pl.application.cocktailVibe.model.User;
import pl.application.cocktailVibe.repository.UserRepository;

@Service
public class UserSaveService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSaveService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerNewUserAccount(UserDTO userDto)  {
        User user = new User();
        user.setNickName(userDto.getNickName());
        user.setEmail(userDto.getEmail());
        user.setPassword(hashPassword(userDto.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }

    private String hashPassword(String password){
        return passwordEncoder.encode(password);
    }
}
