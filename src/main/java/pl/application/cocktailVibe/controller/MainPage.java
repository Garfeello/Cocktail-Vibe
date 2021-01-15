package pl.application.cocktailVibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.repository.CocktailRepository;

import java.util.List;

@Controller
@RequestMapping("/cocktailVibe")
public class MainPage {

    private final CocktailRepository cocktailRepository;

    public MainPage(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    @ModelAttribute("fiveNewestDrinks")
    private List<Cocktail> newestDrinksList() {
        return cocktailRepository.findFiveNewestCocktails();
    }

    @GetMapping("/")
    private String mainPage(){
        return "mainPage/body";
    }

}
