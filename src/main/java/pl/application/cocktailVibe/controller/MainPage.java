package pl.application.cocktailVibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.repository.AlcoholRepository;
import pl.application.cocktailVibe.repository.CocktailRepository;
import pl.application.cocktailVibe.repository.IngredientRepository;
import pl.application.cocktailVibe.services.CocktailDbService;
import pl.application.cocktailVibe.services.GoogleTranslateService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cocktailVibe")
public class MainPage {

    private final GoogleTranslateService googleTranslateService;
    private final CocktailRepository cocktailRepository;
    private final CocktailDbService cocktailDbService;
    private final IngredientRepository ingredientRepository;
    private final AlcoholRepository alcoholRepository;


    public MainPage(GoogleTranslateService googleTranslateService, CocktailRepository cocktailRepository,
                    CocktailDbService cocktailDbService, IngredientRepository ingredientRepository, AlcoholRepository alcoholRepository) {
        this.googleTranslateService = googleTranslateService;
        this.cocktailRepository = cocktailRepository;
        this.cocktailDbService = cocktailDbService;
        this.ingredientRepository = ingredientRepository;
        this.alcoholRepository = alcoholRepository;
    }

    @ModelAttribute("fiveNewestCocktails")
    private List<Cocktail> newestCocktailList() {
        return cocktailRepository.findFiveNewestCocktails();
    }

    @GetMapping("/")
    private String mainPage() {
        return "mainPage/body";
    }

    @GetMapping("/search")
    private String search(@RequestParam String searchedString, Model model) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findFirstByName(searchedString);
        Optional<Cocktail> cocktailOptional = cocktailRepository.findFirstByName(searchedString);
        Optional<Alcohol> alcoholOptional = alcoholRepository.findFirstByName(searchedString);
        model.addAttribute("searchedString", "- Searching by " + searchedString);

        if (ingredientOptional.isPresent()) {
            model.addAttribute("cocktail", cocktailRepository.findCocktailsByIngredients(ingredientOptional.get()).get());
        } else if (cocktailOptional.isPresent()) {
            model.addAttribute("cocktail", List.of(cocktailOptional.get()));
        } else if (alcoholOptional.isPresent()) {
            model.addAttribute("cocktail", cocktailRepository.findCocktailsByAlcoholList(alcoholOptional.get()).get());
        }
        return "cocktail/cocktailInfo";
    }


    //////////////////////////////////////////////////////////////////////////////////////
    @ResponseBody
    @GetMapping("/test")
    public Cocktail cocktail() {
        cocktailDbService.getCocktail("Kir");
        return cocktailRepository.findFirstByName("Kir").orElse(new Cocktail());
    }

    @ResponseBody
    @GetMapping("/testList")
    private List<Cocktail> cocktailList() {
        return cocktailDbService.getCocktailsByIngredient("caruva");

    }

    @ResponseBody
    @GetMapping("/testAlcohol")
    private List<Cocktail> cocktailListAlcohol() {
        return cocktailDbService.getCocktailsByAlcohol("brandy");
    }

}
