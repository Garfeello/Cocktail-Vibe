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
import pl.application.cocktailVibe.services.CocktailDTOService;
import pl.application.cocktailVibe.services.CocktailService;
import pl.application.cocktailVibe.services.GoogleTranslateService;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/cocktailVibe")
public class MainPageController {

    private final CocktailRepository cocktailRepository;
    private final IngredientRepository ingredientRepository;
    private final AlcoholRepository alcoholRepository;
    private final CocktailDTOService cocktailDTOService;
    private final CocktailService cocktailService;
    private final GoogleTranslateService googleTranslateService;

    public MainPageController(CocktailRepository cocktailRepository, IngredientRepository ingredientRepository,
                              AlcoholRepository alcoholRepository, CocktailDTOService cocktailDTOService,
                              CocktailService cocktailService, GoogleTranslateService googleTranslateService) {
        this.cocktailRepository = cocktailRepository;
        this.ingredientRepository = ingredientRepository;
        this.alcoholRepository = alcoholRepository;
        this.cocktailDTOService = cocktailDTOService;
        this.cocktailService = cocktailService;
        this.googleTranslateService = googleTranslateService;
    }

    @ModelAttribute("fiveNewestCocktails")
    private List<Cocktail> newestCocktailList(Locale locale) {
        return cocktailRepository.findSixNewestCocktails(locale.getLanguage()).orElse(Collections.emptyList());
    }

    @GetMapping("/")
    private String mainPage() {
        return "mainPage/body";
    }

    @GetMapping("/search")
    private String search(@RequestParam(required = false)String searchedString, Model model, Locale locale) {
        Optional<List<Cocktail>> cocktailsOptional = cocktailRepository.findCocktailsByNameAndLanguage(searchedString, locale.getLanguage());
        Optional<Ingredient> ingredientOptional = ingredientRepository.findFirstByNameAndLanguage(searchedString, locale.getLanguage());
        Optional<Alcohol> alcoholOptional = alcoholRepository.findFirstByNameAndLanguage(searchedString, locale.getLanguage());
        Cocktail cocktail = new Cocktail();
        if (cocktailsOptional.isPresent()) {
            model.addAttribute("cocktail", cocktailsOptional.get());
        } else if (ingredientOptional.isPresent()) {
            model.addAttribute("cocktail", cocktailRepository.findCocktailsByIngredients(ingredientOptional.get()).get());
        } else if (alcoholOptional.isPresent()) {
            alcoholOptional.ifPresent(alcohol -> model.addAttribute("cocktail", cocktailRepository.findCocktailsByAlcoholList(alcohol).get()));
        } else {
            searchedString = searchedString == null ? " " : searchedString;
            cocktail = cocktailService.getCocktail(cocktailDTOService.getCocktailDto(searchedString));
            model.addAttribute("cocktail", List.of(cocktail));
        }

        if (cocktail.getName() != null) {
            String translateTo = locale.getLanguage().equals("pl") ? "en" : "pl";
            cocktailRepository.save(cocktail);
            cocktailRepository.save(googleTranslateService.translateAngGetCocktail(cocktail.getName(), locale.getLanguage(), translateTo));
        }

        model.addAttribute("searchedString", "Searching by " + searchedString);
        return "cocktail/cocktailInfo";
    }
}
