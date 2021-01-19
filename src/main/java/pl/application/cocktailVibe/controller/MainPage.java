package pl.application.cocktailVibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.repository.CocktailRepository;
import pl.application.cocktailVibe.services.CocktailDbService;
import pl.application.cocktailVibe.services.GoogleTranslateService;

import java.util.List;

@Controller
@RequestMapping("/cocktailVibe")
public class MainPage {

    private final GoogleTranslateService googleTranslateService;
    private final CocktailRepository cocktailRepository;
    private final CocktailDbService cocktailDbService;

    public MainPage(GoogleTranslateService googleTranslateService, CocktailRepository cocktailRepository, CocktailDbService cocktailDbService) {
        this.googleTranslateService = googleTranslateService;
        this.cocktailRepository = cocktailRepository;
        this.cocktailDbService = cocktailDbService;
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
    public Cocktail cocktail() {
        cocktailDbService.checkIfCocktailExists("Martini");
        googleTranslateService.translateCocktail("Martini");
        return cocktailRepository.findFirstByName("Martini").orElse(new Cocktail());
    }


    @ResponseBody
    @GetMapping("/testList")
    private List<Cocktail> cocktailList() {
        return cocktailDbService.searchCocktailByIngredient("Lime juice");
    }
}
