package pl.application.cocktailVibe.services;

import org.springframework.stereotype.Component;
import pl.application.cocktailVibe.apiIntegration.TheCocktailDbAPI;
import pl.application.cocktailVibe.model.Cocktail;

@Component
public class CocktailDbService {

    private final TheCocktailDbAPI theCocktailDbAPI;


    public CocktailDbService(TheCocktailDbAPI theCocktailDbAPI) {
        this.theCocktailDbAPI = theCocktailDbAPI;
    }

    public void searchCocktailByName(String cocktailName) {
       String searchByCocktailNameUrl = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=$";
       theCocktailDbAPI.getAndSaveCocktail(searchByCocktailNameUrl.replace("$", cocktailName));
    }

    public void searchCocktailByIngredient(String cocktailName) {
        String searchByCocktailIngredientUrl = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=$";

    }

    public void searchCocktailByMultipleIngredients(String... cocktailName) {
        String searchByMultipleIngredients = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=$";

    }
}
