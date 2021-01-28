package pl.application.cocktailVibe.services;

import org.springframework.stereotype.Component;
import pl.application.cocktailVibe.apiIntegration.CocktailDbAPI;
import pl.application.cocktailVibe.wrapper.ApiObjectsWrapper;

@Component
public class CocktailDbApiService {

    private final CocktailDbAPI cocktailDbAPI;

    public CocktailDbApiService(CocktailDbAPI cocktailDbAPI) {
        this.cocktailDbAPI = cocktailDbAPI;
    }

    public void test(int drinkId){
        ApiObjectsWrapper apiObjectsWrapper = cocktailDbAPI.getApiObjectFromDrinkId(drinkId);
        System.out.println(apiObjectsWrapper);
    }




}
