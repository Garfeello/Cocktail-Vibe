package pl.application.cocktailVibe.services;

import org.springframework.stereotype.Component;
import pl.application.cocktailVibe.apiIntegration.TheCocktailDbAPI;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.repository.CocktailRepository;
import pl.application.cocktailVibe.repository.IngredientRepository;

import java.util.List;


@Component
public class CocktailDbService {

    private final TheCocktailDbAPI theCocktailDbAPI;
    private final CocktailRepository cocktailRepository;
    private final IngredientRepository ingredientRepository;

    public CocktailDbService(TheCocktailDbAPI theCocktailDbAPI, CocktailRepository cocktailRepository, IngredientRepository ingredientRepository) {
        this.theCocktailDbAPI = theCocktailDbAPI;
        this.cocktailRepository = cocktailRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public Cocktail checkIfCocktailExists(String cocktailName) {
        if (cocktailRepository.findFirstByName(cocktailName).isPresent()) {
            Cocktail cocktail = cocktailRepository.findFirstByName(cocktailName).get();
            return cocktail;
        } else {
            queryApi(cocktailName);
            Cocktail cocktail = cocktailRepository.findFirstByName(cocktailName).get();
            return cocktail;
        }
    }

    public List<Cocktail> searchCocktailByIngredient(String ingredientName) {
        String searchByCocktailIngredientUrl = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=$";
        if (ingredientRepository.findFirstByName(ingredientName).isPresent()){
            Ingredient ingredient = ingredientRepository.findFirstByName(ingredientName).get();
            return cocktailRepository.findCocktailsByIngredients(ingredient);
        } else {

        }
        return null;
    }

    public void searchCocktailByMultipleIngredients(String... cocktailName) {
        String searchByMultipleIngredients = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=$";
    }


    private void queryApi(String cocktailName) {
        String searchByCocktailName = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=$";
        theCocktailDbAPI.findAndSaveCocktail(searchByCocktailName.replace("$", cocktailName));
    }


}
