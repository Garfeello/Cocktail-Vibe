package pl.application.cocktailVibe.services;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.application.cocktailVibe.dto.UserDTO;
import pl.application.cocktailVibe.model.User;
import pl.application.cocktailVibe.repository.UserRepository;

@Service
public class UserSaveService {

    private final UserRepository userRepository;

    public UserSaveService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
