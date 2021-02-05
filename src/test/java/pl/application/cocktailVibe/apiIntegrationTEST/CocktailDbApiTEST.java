package pl.application.cocktailVibe.apiIntegrationTEST;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.application.cocktailVibe.apiIntegration.CocktailDbAPI;
import pl.application.cocktailVibe.wrapper.ApiObjectsWrapper;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CocktailDbApiTEST {

    @Autowired
    private CocktailDbAPI cocktailDbAPI;

    @Test
    public void givenWrongCocktailNameWhenTryToFindThenExpectNullValues(){
        //given
        String drinkName = "XYZ";
        //when
        ApiObjectsWrapper apiObjectsWrapper = cocktailDbAPI.getApiObjectFromDrinkName(drinkName);
        //then
        assertNull(apiObjectsWrapper.getDrinkApiModel());
        assertNull(apiObjectsWrapper.getIngredientApiModelList());
    }

    @Test
    public void givenCorrectCocktailNameWhenFindCocktailThenGetCorrectCocktailWithIngredients(){
        //given
        String cocktail = "Margarita";
        //when
        ApiObjectsWrapper apiObjectsWrapper = cocktailDbAPI.getApiObjectFromDrinkName(cocktail);
        //then
        assertNotNull(apiObjectsWrapper.getDrinkApiModel());
        assertNotNull(apiObjectsWrapper.getIngredientApiModelList());
        assertEquals(cocktail, apiObjectsWrapper.getDrinkApiModel().getStrDrink());
    }
}
