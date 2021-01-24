package pl.application.cocktailVibe.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.application.cocktailVibe.securityInterface.SecurityInterface;
import pl.application.cocktailVibe.services.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityInterface userDetailsService;

    public SecurityConfig(SecurityInterface userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/pictureController/getPicture/*").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/cocktailVibe/").permitAll()
                .antMatchers("/cocktailVibe/cocktailList").permitAll()
                .antMatchers("/cocktailVibe/cocktailListPl").permitAll()
                .antMatchers("/cocktailVibe/translateCocktail").permitAll()
                .antMatchers("/cocktailVibe/alcoholList").permitAll()
                .antMatchers("/cocktailVibe/alcoholListPl").permitAll()
                .antMatchers("/cocktailVibe/translateAlcohol").permitAll()
                .antMatchers("/cocktailVibe/addCocktail").hasRole("USER")
                .antMatchers("/cocktailVibe/addAlcohol").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
//                .loginProcessingUrl("/performLogin")
                .defaultSuccessUrl("/cocktailVibe/", true);
//                .failureUrl("/login?errorLogin=wrong email or password")
//                .and()
//                .logout()
//                .logoutUrl("/perform_logout")
//                .deleteCookies("JSESSIONID");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
