package pl.application.cocktailVibe.services;

import org.springframework.stereotype.Component;
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

    public Cocktail translateAngGetCocktail(String cocktailName) {
        Optional<Cocktail> cocktailOptional = cocktailRepository.findFirstByNameAndLanguage(cocktailName, "Pl");
        if (cocktailOptional.isEmpty()) {
            cocktailOptional = cocktailRepository.findFirstByName(cocktailName);
            cocktailOptional.ifPresent(this::translateCocktail);
            cocktailOptional = cocktailRepository.findFirstByNameAndLanguage(cocktailName, "Pl");
        }
        return cocktailOptional.orElse(new Cocktail());
    }

    public Alcohol translateAndGetAlcohol(String alcoholName) {
        Optional<Alcohol> alcoholOptional = alcoholRepository.findFirstByNameAndLanguage(alcoholName, "Pl");
        if (alcoholOptional.isEmpty()) {
            alcoholOptional = alcoholRepository.findFirstByName(alcoholName);
            alcoholOptional.ifPresent(this::translateAlcohol);
            alcoholOptional = alcoholRepository.findFirstByNameAndLanguage(alcoholName, "Pl");
        }
        return alcoholOptional.orElse(new Alcohol());
    }

    public Ingredient translateAndGetIngredient(String ingredientName){
        Optional<Ingredient> ingredientOptional = ingredientRepository.findFirstByNameAndLanguage(ingredientName, "PL");
        if (ingredientOptional.isEmpty()){
            ingredientOptional = ingredientRepository.findFirstByName(ingredientName);
            ingredientOptional.ifPresent(this::translateIngredient);
            ingredientOptional = ingredientRepository.findFirstByNameAndLanguage(ingredientName, "PL");
        }
        return ingredientOptional.orElse(new Ingredient());
    }


    private void translateIngredient(Ingredient ingredient) {
        try {
            googleTranslateAPI.translateAndSaveIngredient(ingredient);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    private void translateCocktail(Cocktail cocktail) {
        try {
            googleTranslateAPI.translateAndSaveCocktail(cocktail);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    private void translateAlcohol(Alcohol alcohol) {
        try {
            googleTranslateAPI.translateAndSaveAlcohol(alcohol);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }


}
