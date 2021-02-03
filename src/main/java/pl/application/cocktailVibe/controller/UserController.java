package pl.application.cocktailVibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.model.User;
import pl.application.cocktailVibe.repository.AlcoholRepository;
import pl.application.cocktailVibe.repository.CocktailRepository;
import pl.application.cocktailVibe.repository.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/cocktailVibe/user")
public class UserController {

    private final UserRepository userRepository;
    private final CocktailRepository cocktailRepository;
    private final AlcoholRepository alcoholRepository;

    public UserController(UserRepository userRepository, CocktailRepository cocktailRepository,
                          AlcoholRepository alcoholRepository) {
        this.userRepository = userRepository;
        this.cocktailRepository = cocktailRepository;
        this.alcoholRepository = alcoholRepository;
    }

    @GetMapping("/cocktails")
    private String getUserCocktails(Principal principal, Model model, Locale locale) {
        String getUserEmail = principal.getName();
        Optional<User> optionalUser = userRepository.findByEmail(getUserEmail);
        Optional<List<Cocktail>> cocktailsByUser = cocktailRepository.findCocktailsByUserAndLanguage(optionalUser.orElse(new User()), locale.getLanguage());
        cocktailsByUser.ifPresent(cocktails -> model.addAttribute("cocktailList", cocktails));
        return "cocktail/userCocktails";
    }

    @GetMapping("/alcohols")
    private String getUserAlcohols(Principal principal, Model model, Locale locale) {
        String getUserEmail = principal.getName();
        Optional<User> optionalUser = userRepository.findByEmail(getUserEmail);
        Optional<List<Alcohol>> alcoholsByUser = alcoholRepository.findAllByUserAndLanguage(optionalUser.orElse(new User()), locale.getLanguage());
        alcoholsByUser.ifPresent(alcohols -> model.addAttribute("alcoholList", alcohols));
        return "alcohol/userAlcohols";
    }
}
