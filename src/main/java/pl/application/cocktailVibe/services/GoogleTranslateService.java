package pl.application.cocktailVibe.services;


import org.springframework.stereotype.Service;
import pl.application.cocktailVibe.apiIntegration.GoogleTranslateAPI;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.repository.AlcoholRepository;
import pl.application.cocktailVibe.repository.CocktailRepository;
import pl.application.cocktailVibe.repository.IngredientRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GoogleTranslateService {

    private final GoogleTranslateAPI googleTranslateAPI;
    private final CocktailRepository cocktailRepository;
    private final AlcoholRepository alcoholRepository;
    private final IngredientRepository ingredientRepository;

    public GoogleTranslateService(GoogleTranslateAPI googleTranslateAPI, CocktailRepository cocktailRepository,
                                  AlcoholRepository alcoholRepository, IngredientRepository ingredientRepository) {
        this.googleTranslateAPI = googleTranslateAPI;
        this.cocktailRepository = cocktailRepository;
        this.alcoholRepository = alcoholRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public Cocktail translateAngGetCocktail(String cocktailName, String translatedFrom, String translatedTo) {
        Optional<Cocktail> cocktailOptional = cocktailRepository.findFirstByNameAndLanguage(cocktailName, translatedTo);
        if (cocktailOptional.isEmpty()) {
            cocktailOptional = cocktailRepository.findFirstByName(cocktailName);
        } else {
            return cocktailOptional.get();
        }
        return googleTranslateAPI.translateCocktail(cocktailOptional.get(), translatedFrom, translatedTo);
    }

    public Alcohol translateAndGetAlcohol(String alcoholName, String translatedTo) {
        Optional<Alcohol> alcoholOptional = alcoholRepository.findFirstByNameAndLanguage(alcoholName, "Pl");
        if (alcoholOptional.isEmpty()) {
            alcoholOptional = alcoholRepository.findFirstByName(alcoholName);
        } else {
            return alcoholOptional.get();
        }
        return googleTranslateAPI.translateAndSaveAlcohol(cocktailOptional.get(), translatedFrom, translatedTo);
    }

    public Ingredient translateAndGetIngredient(String ingredientName, String translatedFrom, String translatedTo) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findFirstByNameAndLanguage(ingredientName, "PL");
        if (ingredientOptional.isEmpty()) {
            ingredientOptional = ingredientRepository.findFirstByName(ingredientName);
            ingredientOptional.ifPresent(ingredient -> translateIngredient(ingredient, translatedFrom, translatedTo));
            ingredientOptional = ingredientRepository.findFirstByNameAndLanguage(ingredientName, "PL");
        }
        return ingredientOptional.orElse(new Ingredient());
    }

    private void translateIngredient(Ingredient ingredient, String translatedFrom, String translatedTo) {
        try {
            googleTranslateAPI.translateAndSaveIngredient(ingredient, translatedFrom, translatedTo);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

}
