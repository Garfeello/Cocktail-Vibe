package pl.application.cocktailVibe.apiIntegration;

import org.springframework.stereotype.Component;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.repository.CocktailRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GoogleTranslateAPI_v2 {

    private final CocktailRepository cocktailRepository;

    public GoogleTranslateAPI_v2(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    public void translateAndSaveCocktail(String cocktailName) {
        cocktailRepository.save(translateCocktail(cocktailName));
    }

    private Cocktail translateCocktail(String cocktailName) {
        Cocktail cocktail = cocktailRepository.findFirstByName(cocktailName).orElseThrow();
        Cocktail translatedCocktail = new Cocktail();
        translatedCocktail.setName(cocktail.getName());
        translatedCocktail.setIngredients(translateIngredients(cocktail.getIngredients()));
        translatedCocktail.setAlcoholList(translateAlcohols(cocktail.getAlcoholList()));
        translatedCocktail.setPreparationDescription(translatePreparationDescription(cocktail.getPreparationDescription()));
        translatedCocktail.setLanguage("Pl");
        translatedCocktail.setUserInspiration("przykładowa inspiracja użytkownika");
        translatedCocktail.setPicture(cocktail.getPicture());
        return translatedCocktail;
    }

    //google allows max 5000 characters per day so, on free account i cant translate every description unfortunately :(
    //It is only for academical purposes after all :)
    private String translatePreparationDescription(String description) {
        return prepareAndGetTranslation(description);
    }

    private List<Ingredient> translateIngredients(List<Ingredient> ingredientList) {
        List<Ingredient> translatedIngredients = new ArrayList<>();
        for (Ingredient ingredient : ingredientList) {
            Ingredient newIngredient = new Ingredient();
            newIngredient.setLanguage("Pl");
            newIngredient.setName(prepareAndGetTranslation(ingredient.getName()));
            newIngredient.setType(prepareAndGetTranslation(ingredient.getType()));
            newIngredient.setDescription("przykładowy opis składnika w języku polskim");
            translatedIngredients.add(newIngredient);
        }
        return translatedIngredients;
    }

    //here should be alcohol translation implementation which is not included due to reasons mentioned above.
    private List<Alcohol> translateAlcohols(List<Alcohol> alcoholList) {
        List<Alcohol> copiedList = new ArrayList<>();
        for (Alcohol alcohol : alcoholList) {
            Alcohol alcoholForTranslation = new Alcohol();
            alcoholForTranslation.setLanguage("Pl");
            alcoholForTranslation.setName(alcohol.getName());
            alcoholForTranslation.setAlcoholType(alcohol.getAlcoholType());
            alcoholForTranslation.setAge(0);
            alcoholForTranslation.setDescription("przykładowy opis Alkoholu");
            copiedList.add(alcoholForTranslation);
        }
        return copiedList;
    }

    private String prepareAndGetTranslation(String text) {
        String translation = "Couldn't translate";
        try {
            translation = translate("en", "pl", text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return translation;
    }

    private String translate(String langFrom, String langTo, String text) throws IOException {
        String checkedText = Optional.ofNullable(text).orElse("empty");
        String translateScriptUrl = "https://script.google.com/macros/s/AKfycbxmTKMvaCh9H6Plwhw2AOWMeKbArfEP4LFubJTzgTvdcRaGAr1B/exec";
        String urlStr = translateScriptUrl + "?q=" + URLEncoder.encode(checkedText, "UTF-8") + "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        StringBuilder response = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();

        return response.toString();
    }
}
