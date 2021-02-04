package pl.application.cocktailVibe.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.application.cocktailVibe.apiIntegration.CocktailDbAPI;
import pl.application.cocktailVibe.model.User;
import pl.application.cocktailVibe.repository.UserRepository;
import pl.application.cocktailVibe.securityInterface.SecurityInterface;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MyUserDetailsService implements SecurityInterface {

    private final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String email)  {
        User user = getUser(email);
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                List.of(getAuthority(user.getRole())));
    }

    private User getUser(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            logger.warn("No user found with username: " + email + " " + LocalDateTime.now());
        }
        return optionalUser.get();
    }

    private GrantedAuthority getAuthority(String roles) {
        return new SimpleGrantedAuthority(roles);
    }
}