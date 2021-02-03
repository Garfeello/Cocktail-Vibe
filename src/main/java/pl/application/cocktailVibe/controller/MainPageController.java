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
import pl.application.cocktailVibe.services.CocktailService;
import pl.application.cocktailVibe.services.GoogleTranslateService;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/cocktailVibe")
public class MainPageController {

    private final CocktailRepository cocktailRepository;
    private final IngredientRepository ingredientRepository;
    private final AlcoholRepository alcoholRepository;
    private final CocktailDbApiService cocktailDbApiService;
    private final CocktailService cocktailService;
    private final GoogleTranslateService googleTranslateService;

    public MainPageController(CocktailRepository cocktailRepository, IngredientRepository ingredientRepository,
                              AlcoholRepository alcoholRepository, CocktailDbApiService cocktailDbApiService,
                              CocktailService cocktailService, GoogleTranslateService googleTranslateService) {
        this.cocktailRepository = cocktailRepository;
        this.ingredientRepository = ingredientRepository;
        this.alcoholRepository = alcoholRepository;
        this.cocktailDbApiService = cocktailDbApiService;
        this.cocktailService = cocktailService;
        this.googleTranslateService = googleTranslateService;
    }

    @ModelAttribute("fiveNewestCocktails")
    private List<Cocktail> newestCocktailList(Locale locale) {
        return cocktailRepository.findSixNewestCocktails(locale.getLanguage());
    }

    @GetMapping("/")
    private String mainPage() {
        return "mainPage/body";
    }

    @GetMapping("/search")
    private String search(@RequestParam(required = false) String searchedString, Model model) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findFirstByName(searchedString);
        Optional<Cocktail> cocktailOptional = cocktailRepository.findFirstByName(searchedString);
        Optional<Alcohol> alcoholOptional = alcoholRepository.findFirstByName(searchedString);
        Cocktail cocktail = new Cocktail();
        if (ingredientOptional.isPresent()) {
            model.addAttribute("cocktail", cocktailRepository.findCocktailsByIngredients(ingredientOptional.get()).get());
        } else if (cocktailOptional.isPresent()) {
            model.addAttribute("cocktail", List.of(cocktailOptional.get()));
        } else if (alcoholOptional.isPresent()) {
            alcoholOptional.ifPresent(alcohol -> model.addAttribute("cocktail", cocktailRepository.findCocktailsByAlcoholList(alcohol).get()));
        } else {
            cocktail = cocktailService.getCocktail(cocktailDbApiService.getCocktailDto(searchedString));
            model.addAttribute("cocktail", List.of(cocktail));
        }

        if (cocktail.getName() != null) {
            cocktailRepository.save(cocktail);
            cocktailRepository.save(googleTranslateService.translateAngGetCocktail(cocktail.getName(), "en", "pl"));
        }
        model.addAttribute("searchedString", "Searching by " + searchedString);
        return "cocktail/cocktailInfo";
    }
}
