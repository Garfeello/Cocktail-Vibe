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
                .antMatchers("/pictureController/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/logoutSuccess").permitAll()
                .antMatchers("/cocktailVibe/").permitAll()
                .antMatchers("/cocktailVibe/getCocktailsFromIngredient").permitAll()
                .antMatchers("/cocktailVibe/search").permitAll()
                .antMatchers("/cocktailVibe/cocktailList").permitAll()
                .antMatchers("/cocktailVibe/ingredient/ingredientList").permitAll()
                .antMatchers("/cocktailVibe/ingredient/translateIngredient").permitAll()
                .antMatchers("/cocktailVibe/translateCocktail").permitAll()
                .antMatchers("/cocktailVibe/alcoholList").permitAll()
                .antMatchers("/cocktailVibe/alcoholListPl").permitAll()
                .antMatchers("/cocktailVibe/translateAlcohol").permitAll()
                .antMatchers("/cocktailVibe/user/*").hasRole("USER")
                .antMatchers("/cocktailVibe/ingredient/addIngredient").hasRole("USER")
                .antMatchers("/cocktailVibe/addCocktail").hasRole("USER")
                .antMatchers("/cocktailVibe/addAlcohol").hasRole("USER")
                .antMatchers("/cocktailVibe/editCocktail").hasRole("USER")
                .antMatchers("/cocktailVibe/editAlcohol").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/cocktailVibe/")
                .and()
                .logout()
                .logoutSuccessUrl("/logoutSuccess")
                .deleteCookies("JSESSIONID");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
