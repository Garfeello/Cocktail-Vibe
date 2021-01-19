package pl.application.cocktailVibe.services;

import org.springframework.stereotype.Component;
import pl.application.cocktailVibe.apiIntegration.TheCocktailDbAPI;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.repository.CocktailRepository;


@Component
public class CocktailDbService {

    private final TheCocktailDbAPI theCocktailDbAPI;
    private final GoogleTranslateService googleTranslateService;
    private final CocktailRepository cocktailRepository;

    public CocktailDbService(TheCocktailDbAPI theCocktailDbAPI, GoogleTranslateService googleTranslateService, CocktailRepository cocktailRepository) {
        this.theCocktailDbAPI = theCocktailDbAPI;
        this.googleTranslateService = googleTranslateService;
        this.cocktailRepository = cocktailRepository;
    }

    public Cocktail searchCocktailByName(String cocktailName) {
        if (cocktailRepository.findFirstByName(cocktailName).isPresent()) {
            Cocktail cocktail = cocktailRepository.findFirstByName(cocktailName).get();
            return cocktail;
        } else {
            queryApi(cocktailName);
            Cocktail cocktail = cocktailRepository.findFirstByName(cocktailName).get();
            return cocktail;
        }
    }


    public void searchCocktailByIngredient(String cocktailName) {
        String searchByCocktailIngredientUrl = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=$";
    }

    public void searchCocktailByMultipleIngredients(String... cocktailName) {
        String searchByMultipleIngredients = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=$";
    }


    private void queryApi(String cocktailName) {
        String searchByCocktailName = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=$";
        theCocktailDbAPI.findAndSaveCocktail(searchByCocktailName.replace("$", cocktailName));
        googleTranslateService.translateAndSaveCocktail(cocktailName);
    }


}
