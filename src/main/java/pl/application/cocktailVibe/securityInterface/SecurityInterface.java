package pl.application.cocktailVibe.securityInterface;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface SecurityInterface extends UserDetailsService {

    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
