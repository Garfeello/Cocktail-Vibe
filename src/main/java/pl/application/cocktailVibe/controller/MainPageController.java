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
import pl.application.cocktailVibe.services.CocktailDbApiService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cocktailVibe")
public class MainPageController {

    private final CocktailRepository cocktailRepository;
    private final IngredientRepository ingredientRepository;
    private final AlcoholRepository alcoholRepository;
    private final CocktailDbApiService cocktailDbApiService;

    public MainPageController(CocktailRepository cocktailRepository, IngredientRepository ingredientRepository,
                              AlcoholRepository alcoholRepository, CocktailDbApiService cocktailDbApiService) {
        this.cocktailRepository = cocktailRepository;
        this.ingredientRepository = ingredientRepository;
        this.alcoholRepository = alcoholRepository;
        this.cocktailDbApiService = cocktailDbApiService;
    }

    @ModelAttribute("fiveNewestCocktails")
    private List<Cocktail> newestCocktailList() {
        return cocktailRepository.findSixNewestCocktails();
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
        model.addAttribute("searchedString", "Searching by " + searchedString);

        if (ingredientOptional.isPresent()) {
            model.addAttribute("cocktail", cocktailRepository.findCocktailsByIngredients(ingredientOptional.get()).get());
        } else if (cocktailOptional.isPresent()) {
            model.addAttribute("cocktail", List.of(cocktailOptional.get()));
        } else
            alcoholOptional.ifPresent(alcohol -> model.addAttribute("cocktail", cocktailRepository.findCocktailsByAlcoholList(alcohol).get()));
        return "cocktail/cocktailInfo";
    }

    //////////////////////////////////////////////////////////////////////////////////////
    @ResponseBody
    @GetMapping("/testAlcohol")
    private void cocktailListAlcohol() {
        cocktailDbApiService.test(11007);
    }

}
