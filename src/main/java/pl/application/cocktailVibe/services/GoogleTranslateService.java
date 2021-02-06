package pl.application.cocktailVibe.services;

import org.springframework.stereotype.Service;
import pl.application.cocktailVibe.apiIntegration.GoogleTranslateAPI;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.repository.AlcoholRepository;
import pl.application.cocktailVibe.repository.CocktailRepository;
import pl.application.cocktailVibe.repository.IngredientRepository;

import java.util.ArrayList;
import java.util.List;
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
        return translateCocktail(cocktailOptional.get(), translatedFrom, translatedTo);
    }

    public Alcohol translateAndGetAlcohol(String alcoholName, String translatedTo) {
        Optional<Alcohol> alcoholOptional = alcoholRepository.findFirstByNameAndLanguage(alcoholName, translatedTo);
        if (alcoholOptional.isEmpty()) {
            alcoholOptional = alcoholRepository.findFirstByName(alcoholName);
        } else {
            return alcoholOptional.get();
        }
        return translateAlcohols(List.of(alcoholOptional.get()), translatedTo).get(0);
    }

    private Cocktail translateCocktail(Cocktail cocktail, String translatedFrom, String translatedTo) {
        Cocktail translatedCocktail = new Cocktail();
        translatedCocktail.setName(cocktail.getName());
        translatedCocktail.setIngredients(translateIngredients(cocktail.getIngredients(), translatedFrom, translatedTo));
        translatedCocktail.setAlcoholList(translateAlcohols(cocktail.getAlcoholList(), translatedTo));
        translatedCocktail.setPreparationDescription(googleTranslateAPI.prepareAndGetTranslation(cocktail.getPreparationDescription(), translatedFrom, translatedTo));
        translatedCocktail.setLanguage(translatedTo);
        translatedCocktail.setPicture(cocktail.getPicture());
        return translatedCocktail;
    }

    private List<Ingredient> translateIngredients(List<Ingredient> ingredientList, String translatedFrom, String translatedTo) {
        List<Ingredient> translatedIngredients = new ArrayList<>();
        for (Ingredient ingredient : ingredientList) {
            Optional<Ingredient> optionalIngredient = ingredientRepository.findFirstByNameAndLanguage(ingredient.getName(), translatedTo);
            if (optionalIngredient.isPresent()) {
                translatedIngredients.add(optionalIngredient.get());
            } else {
                Ingredient newIngredient = new Ingredient();
                newIngredient.setLanguage(translatedTo);
                newIngredient.setName(googleTranslateAPI.prepareAndGetTranslation(ingredient.getName(), translatedFrom, translatedTo));
                newIngredient.setType(googleTranslateAPI.prepareAndGetTranslation(ingredient.getType(), translatedFrom, translatedTo));
                translatedIngredients.add(newIngredient);
            }
        }
        return translatedIngredients;
    }

    //google allows max 5000 characters per day so, on free account i cant translate every description unfortunately :(
    //It is only for academical purposes after all :)
    private List<Alcohol> translateAlcohols(List<Alcohol> alcoholList, String translatedTo) {
        List<Alcohol> translatedAlcohols = new ArrayList<>();
        for (Alcohol alcohol : alcoholList) {
            Optional<Alcohol> optionalAlcohol = alcoholRepository.findFirstByNameAndLanguage(alcohol.getName(), translatedTo);
            if (optionalAlcohol.isPresent()) {
                translatedAlcohols.add(optionalAlcohol.get());
            } else {
                Alcohol alcoholForTranslation = new Alcohol();
                alcoholForTranslation.setLanguage(translatedTo);
                alcoholForTranslation.setName(alcohol.getName());
                alcoholForTranslation.setAlcoholType(alcohol.getAlcoholType());
                alcoholForTranslation.setAge(alcohol.getAge());
                alcoholForTranslation.setDescription("przykładowy opis Alkoholu/example alcohol description");
                alcoholForTranslation.setPicture(alcohol.getPicture());
                translatedAlcohols.add(alcoholForTranslation);
            }
        }
        return translatedAlcohols;
    }

}
