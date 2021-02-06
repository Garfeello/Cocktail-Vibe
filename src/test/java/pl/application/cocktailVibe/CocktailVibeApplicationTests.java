package pl.application.cocktailVibe;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pl.application.cocktailVibe.apiIntegrationTEST.CocktailDbApiTEST;
import pl.application.cocktailVibe.apiIntegrationTEST.GoogleTranslationApiTEST;
import pl.application.cocktailVibe.controllerTEST.AlcoholControllerTEST;
import pl.application.cocktailVibe.controllerTEST.CocktailControllerTEST;
import pl.application.cocktailVibe.controllerTEST.MainPageControllerTEST;
import pl.application.cocktailVibe.modelTEST.*;
import pl.application.cocktailVibe.securityTEST.SecuredControllerSpringBootIntegrationTest;
import pl.application.cocktailVibe.serviceTEST.PictureServiceTEST;
import pl.application.cocktailVibe.serviceTEST.UserSaveServiceTEST;

@RunWith(Suite.class)
@Suite.SuiteClasses({CocktailDbApiTEST.class, GoogleTranslationApiTEST.class, AlcoholControllerTEST.class, CocktailControllerTEST.class,
        MainPageControllerTEST.class, PictureTEST.class, SimpleAlcoholTest.class, SimpleCocktailTest.class, SimpleIngredientTest.class,
        SimpleUserTest.class, SecuredControllerSpringBootIntegrationTest.class, PictureServiceTEST.class, UserSaveServiceTEST.class})
public class CocktailVibeApplicationTests {
}
