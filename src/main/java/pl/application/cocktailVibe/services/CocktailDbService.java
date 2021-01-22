package pl.application.cocktailVibe.services;

import org.springframework.stereotype.Service;
import pl.application.cocktailVibe.apiIntegration.TheCocktailDbAPI;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.repository.AlcoholRepository;
import pl.application.cocktailVibe.repository.CocktailRepository;
import pl.application.cocktailVibe.repository.IngredientRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CocktailDbService {

    private final TheCocktailDbAPI theCocktailDbAPI;
    private final CocktailRepository cocktailRepository;
    private final IngredientRepository ingredientRepository;
    private final AlcoholRepository alcoholRepository;


    public CocktailDbService(TheCocktailDbAPI theCocktailDbAPI, CocktailRepository cocktailRepository,
                             IngredientRepository ingredientRepository, AlcoholRepository alcoholRepository) {
        this.theCocktailDbAPI = theCocktailDbAPI;
        this.cocktailRepository = cocktailRepository;
        this.ingredientRepository = ingredientRepository;
        this.alcoholRepository = alcoholRepository;
    }

    public Cocktail getCocktail(String cocktailName) {
        Optional<Cocktail> cocktailOptional = cocktailRepository.findFirstByName(cocktailName);
        if (cocktailOptional.isPresent()) {
            return cocktailOptional.get();
        } else {
            queryApiCocktail(cocktailName);
            return cocktailRepository.findFirstByName(cocktailName).orElse(new Cocktail());
        }
    }

    public List<Cocktail> getCocktailsByIngredient(String ingredientName) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findFirstByName(ingredientName);
        if (optionalIngredient.isPresent()) {
            Ingredient ingredient = optionalIngredient.get();
            return cocktailRepository.findCocktailsByIngredients(ingredient).get();
        } else {
            queryApiCocktailByIngredient(ingredientName);
            Ingredient ingredient = ingredientRepository.findFirstByName(ingredientName).orElse(new Ingredient());
            return cocktailRepository.findCocktailsByIngredients(ingredient).get();
        }
    }

    public List<Cocktail> getCocktailsByAlcohol(String alcoholName) {
        Optional<Alcohol> optionalAlcohol = alcoholRepository.findFirstByName(alcoholName);
        if (optionalAlcohol.isPresent()) {
            Alcohol alcohol = optionalAlcohol.get();
            return cocktailRepository.findCocktailsByAlcoholList(alcohol).get();
        } else {
            queryApiCocktailByIngredient(alcoholName);
            Alcohol alcohol = alcoholRepository.findFirstByName(alcoholName).orElse(new Alcohol());
            return cocktailRepository.findCocktailsByAlcoholList(alcohol).get();
        }
    }

    public void searchCocktailByMultipleIngredients(String... cocktailName) {
    }

    private void queryApiCocktailByIngredient(String ingredientName) {
        String searchByCocktailIngredient = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=$";
        getCocktail(theCocktailDbAPI.getDrinkNameByIngredient(searchByCocktailIngredient.replace("$", ingredientName)));
    }

    private void queryApiCocktail(String cocktailName) {
        String searchByCocktailName = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=$";
        theCocktailDbAPI.findAndSaveCocktail(searchByCocktailName.replace("$", cocktailName));
    }


}
