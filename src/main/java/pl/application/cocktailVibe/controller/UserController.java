package pl.application.cocktailVibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.model.User;
import pl.application.cocktailVibe.repository.CocktailRepository;
import pl.application.cocktailVibe.repository.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cocktailVibe/user")
public class UserController {

    private final UserRepository userRepository;
    private final CocktailRepository cocktailRepository;

    public UserController(UserRepository userRepository, CocktailRepository cocktailRepository) {
        this.userRepository = userRepository;
        this.cocktailRepository = cocktailRepository;
    }

    @GetMapping("/cocktails")
    private String getUserCocktails(Principal principal, Model model){
        String getUserEmail = principal.getName();
        Optional<User> optionalUser = userRepository.findByEmail(getUserEmail);
        Optional<List<Cocktail>> cocktailsByUser = cocktailRepository.findCocktailsByUser(optionalUser.orElse(new User()));
        cocktailsByUser.ifPresent(cocktails -> model.addAttribute("cocktailList", cocktails));
        return "cocktail/userCocktails";
    }

}
