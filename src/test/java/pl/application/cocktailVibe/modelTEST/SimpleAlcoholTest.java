package pl.application.cocktailVibe.modelTEST;


import org.junit.jupiter.api.Test;
import pl.application.cocktailVibe.enums.AlcoholType;
import pl.application.cocktailVibe.enums.Language;
import pl.application.cocktailVibe.model.Alcohol;

public class SimpleAlcoholTest {

    @Test
    public void simpleCreateAlcoholTest(){

        Alcohol alcohol = new Alcohol();
        alcohol.setAge(10);
        alcohol.setAlcoholType(AlcoholType.Brandy);
        alcohol.setDescription("test desc");
        alcohol.setName("Rémy Martin");
        alcohol.setLanguage(Language.Eng);



    }


}
