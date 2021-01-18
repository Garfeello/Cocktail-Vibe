package pl.application.cocktailVibe.services;

import org.springframework.stereotype.Component;
import pl.application.cocktailVibe.apiIntegration.TheCocktailDbAPI;
import pl.application.cocktailVibe.model.Cocktail;

@Component
public class CocktailService {

    private final TheCocktailDbAPI theCocktailDbAPI;


    public CocktailService(TheCocktailDbAPI theCocktailDbAPI) {
        this.theCocktailDbAPI = theCocktailDbAPI;
    }

    public Cocktail searchCocktailByName(String cocktailName) {
       String searchByCocktailNameUrl = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=$";
       return theCocktailDbAPI.createCocktailFromStringUrl(searchByCocktailNameUrl.replace("$", cocktailName));
    }

    public void searchCocktailByIngredient(String cocktailName) {
        String searchByCocktailIngredientUrl = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=$";


    }

    public void searchCocktailByMultipleIngredients(String... cocktailName) {
        String searchByMultipleIngredients = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=$";

    }
}
