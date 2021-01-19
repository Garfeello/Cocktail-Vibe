package pl.application.cocktailVibe.services;

import org.springframework.stereotype.Component;
import pl.application.cocktailVibe.apiIntegration.GoogleTranslateAPI_v2;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.repository.CocktailRepository;

import java.util.ArrayList;

import java.util.List;

@Component
public class GoogleTranslateService {

    private final GoogleTranslateAPI_v2 googleTranslateAPI_v2;
    private final CocktailRepository cocktailRepository;

    public GoogleTranslateService(GoogleTranslateAPI_v2 googleTranslateAPI_v2, CocktailRepository cocktailRepository) {
        this.googleTranslateAPI_v2 = googleTranslateAPI_v2;
        this.cocktailRepository = cocktailRepository;
    }

    public void translateAndSaveCocktail(String cocktailName) {
        cocktailRepository.save(translateCocktail(cocktailName));
    }

    private Cocktail translateCocktail(String cocktailName) {
        Cocktail cocktail = cocktailRepository.findFirstByName(cocktailName).orElseThrow();
        Cocktail translatedCocktail = new Cocktail();
        translatedCocktail.setName(cocktail.getName());
        translatedCocktail.setIngredients(translateIngredients(cocktail.getIngredients()));
        translatedCocktail.setAlcoholList(translateAlcohols(cocktail.getAlcoholList()));
        translatedCocktail.setPreparationDescription(translatePreparationDescription(cocktail.getPreparationDescription()));
        translatedCocktail.setLanguage("Pl");
        translatedCocktail.setUserInspiration("przykładowa inspiracja użytkownika");
        translatedCocktail.setPicture(cocktail.getPicture());
        return translatedCocktail;
    }

    private String translatePreparationDescription(String description) {
        return googleTranslateAPI_v2.translateFromEngToPol(description);
    }

    //google allows max 5000 characters per day so, on free account i cant translate every description unfortunately :(
    //It is only for academical purposes after all :)
    private List<Ingredient> translateIngredients(List<Ingredient> ingredientList) {
        List<Ingredient> translatedIngredients = new ArrayList<>();
        for (Ingredient ingredient : ingredientList) {
            Ingredient newIngredient = new Ingredient();
            newIngredient.setLanguage("Pl");
            newIngredient.setName(googleTranslateAPI_v2.translateFromEngToPol(ingredient.getName()));
            newIngredient.setType(googleTranslateAPI_v2.translateFromEngToPol(ingredient.getType()));
            newIngredient.setDescription("przykładowy opis składnika w języku polskim");
            translatedIngredients.add(newIngredient);
        }
        return translatedIngredients;
    }

    //here should be alcohol translation implementation which is not included due to reasons mentioned above.
    private List<Alcohol> translateAlcohols(List<Alcohol> alcoholList) {
        List<Alcohol> copiedList = new ArrayList<>();
        for (Alcohol alcohol : alcoholList) {
            Alcohol alcoholForTranslation = new Alcohol();
            alcoholForTranslation.setLanguage("Pl");
            alcoholForTranslation.setName(alcohol.getName());
            alcoholForTranslation.setAlcoholType(alcohol.getAlcoholType());
            alcoholForTranslation.setAge(0);
            alcoholForTranslation.setDescription("przykładowy opis Alkoholu");
            copiedList.add(alcoholForTranslation);
        }
        return copiedList;
    }
}
