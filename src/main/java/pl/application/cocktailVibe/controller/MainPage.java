package pl.application.cocktailVibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.repository.CocktailRepository;
import pl.application.cocktailVibe.repository.PictureRepository;
import pl.application.cocktailVibe.services.CocktailService;

import java.util.List;

@Controller
@RequestMapping("/cocktailVibe")
public class MainPage {

    private final CocktailRepository cocktailRepository;
    private final CocktailService cocktailService;

    public MainPage(CocktailRepository cocktailRepository, CocktailService cocktailService) {
        this.cocktailRepository = cocktailRepository;
        this.cocktailService = cocktailService;
    }

    @ModelAttribute("fiveNewestCocktails")
    private List<Cocktail> newestCocktailList() {
        return cocktailRepository.findFiveNewestCocktails();
    }

    @GetMapping("/")
    private String mainPage() {
        return "mainPage/body";
    }

    @ResponseBody
    @GetMapping("/test")
    private Cocktail cocktail() {
        Cocktail cocktail = cocktailService.searchCocktailByName("Blue Margarita");
        return cocktail;
    }

}
