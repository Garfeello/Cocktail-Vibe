package pl.application.cocktailVibe.modelTEST;


import org.junit.jupiter.api.Test;
import pl.application.cocktailVibe.model.Alcohol;

public class SimpleAlcoholTest {

    @Test
    public void simpleCreateAlcoholTest(){

        Alcohol alcohol = new Alcohol();
        alcohol.setAge(10);
        alcohol.setAlcoholType("abc");
        alcohol.setDescription("test desc");
        alcohol.setName("Rémy Martin");



    }


}
