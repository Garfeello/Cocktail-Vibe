package pl.application.cocktailVibe.apiIntegration;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.application.cocktailVibe.apiIntegrationModel.DrinkApiCollection;

import java.io.IOException;
import java.net.URL;


public class test {

    public static void main(String[] args) {
        String resourceURL = "https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=11001";
        ObjectMapper mapper = new ObjectMapper();

        try {
            DrinkApiCollection value = mapper.readValue(new URL(resourceURL), DrinkApiCollection.class);

        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
