package pl.application.cocktailVibe.apiIntegration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.model.Picture;
import pl.application.cocktailVibe.repository.AlcoholRepository;
import pl.application.cocktailVibe.repository.CocktailRepository;
import pl.application.cocktailVibe.repository.IngredientRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Component
public class TheCocktailDbAPI {

    private final CocktailRepository cocktailRepository;
    private final IngredientRepository ingredientRepository;
    private final AlcoholRepository alcoholRepository;

    public TheCocktailDbAPI(CocktailRepository cocktailRepository, IngredientRepository ingredientRepository,
                            AlcoholRepository alcoholRepository) {
        this.cocktailRepository = cocktailRepository;
        this.ingredientRepository = ingredientRepository;
        this.alcoholRepository = alcoholRepository;
    }

    public void findAndSaveCocktail(String resourceURL) {
        try {
            cocktailRepository.save(getCocktailFromApi(resourceURL));
        } catch (ConstraintViolationException e){
            e.getConstraintName();
        }
    }

    public String getDrinkNameByIngredient(String resourceURL) {
        List<String> strings = getListOfStringFromArrayNode(resourceURL);
        List<String> drinkNames = new ArrayList<>();
        for (String string : strings) {
            if (string.matches(".*strDrink\".*")) {
                drinkNames.add(string.replaceAll(".*strDrink\"[:][\"]", "").replace("\"", ""));
            }
        }
        return drinkNames.get(new Random().nextInt(drinkNames.size()));
    }

    private Cocktail getCocktailFromApi(String resourceURL) {
        Cocktail cocktail = new Cocktail();
        String drinkName = "";
        List<Alcohol> alcoholList = new ArrayList<>();
        List<Ingredient> ingredientList = new ArrayList<>();
        String drinkInstructions = "";
        String drinkPictureUrl = "";

        List<String> strings = getListOfStringFromArrayNode(resourceURL);

        // thinking about new method idea
        for (String string : strings) {
            string = string.replaceAll(",", " ");
            if (string.matches(".*strDrink\".*")) {
                drinkName = string.replaceAll(".*strDrink\"[:][\"]", "").replace("\"", "");
            } else if (string.matches("^((?!.*null).).*strIngredient[0-9].*")) {
                String getIngredient = string.replaceAll(".*strIngredient[0-9]\"[:][\"]", "").replace("\"", "");
                checkIngredientOrAlcoholAndCreateObject(getIngredient, alcoholList, ingredientList);
            } else if (string.matches(".*strInstructions\".*")) {
                drinkInstructions = string.replaceAll(".*strInstructions\"[:][\"]", "").replace("\"", "");
            } else if (string.matches(".*strDrinkThumb\".*")) {
                drinkPictureUrl = string.replaceAll(".*strDrinkThumb\"[:][\"]", "").replace("\"", "");
            }
        }
        cocktail.setName(drinkName);
        cocktail.setAlcoholList(alcoholList);
        cocktail.setIngredients(ingredientList);
        cocktail.setPreparationDescription(drinkInstructions);
        cocktail.setPicture(downloadAndCreatePictureFromUrl(drinkPictureUrl, drinkName));
        cocktail.setLanguage("Eng");
        cocktail.setUserInspiration("downloaded data from theCocktailDB");
        return cocktail;
    }

    //create objects connected with cocktail class
    private Picture downloadAndCreatePictureFromUrl(String pictureURL, String cocktailName) {
        Picture picture = new Picture();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            URL url = new URL(pictureURL);
            BufferedImage image = ImageIO.read(url);
            ImageIO.write(image, "jpg", byteArrayOutputStream);
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        picture.setImage(byteArrayOutputStream.toByteArray());
        picture.setName(cocktailName + ".jpg");
        return picture;
    }

    private void checkIngredientOrAlcoholAndCreateObject(String ingredient, List<Alcohol> alcoholList, List<Ingredient> ingredientList) {
        String resourceURL = "https://www.thecocktaildb.com/api/json/v1/1/search.php?i=" + ingredient;
        List<String> stringsFromArrayNode = getListOfStringFromArrayNode(resourceURL);
        for (String string : stringsFromArrayNode) {
            if (string.matches(".*strAlcohol\".*") && string.matches(".*Yes.*")) {
                createAlcohol(ingredient, stringsFromArrayNode, alcoholList);
            } else if (string.matches(".*strAlcohol\".*")) {
                createIngredient(ingredient, stringsFromArrayNode, ingredientList);
            }
        }
    }

    private void createIngredient(String ingredientName, List<String> strings, List<Ingredient> ingredientList) {
        Optional<Ingredient> ingredient = ingredientRepository.findFirstByName(ingredientName);
        if (ingredient.isPresent()) {
            ingredientList.add(ingredient.get());
        } else {
            Ingredient newIngredient = new Ingredient();
            newIngredient.setName(ingredientName);
            newIngredient.setLanguage("Eng");
            for (String string : strings) {
                if (string.matches(".*strDescription\".*")) {
                    String ingredientDescription = string.replaceAll(".*strDescription\"[:][\"]", "");
                    newIngredient.setDescription(ingredientDescription);
                } else if (string.matches(".*strType.*")) {
                    String ingredientType = string.replaceAll(".*strType\"[:][\"]", "").replace("\"", "");
                    newIngredient.setType(ingredientType);
                }
            }
            ingredientList.add(newIngredient);
        }
    }

    private void createAlcohol(String alcoholName, List<String> strings, List<Alcohol> alcoholList) {
        Optional<Alcohol> alcohol = alcoholRepository.findFirstByName(alcoholName);
        if (alcohol.isPresent()) {
            alcoholList.add(alcohol.get());
        } else {
            Alcohol newAlcohol = new Alcohol();
            newAlcohol.setName(alcoholName);
            newAlcohol.setLanguage("Eng");
            newAlcohol.setAge(0);
            for (String string : strings) {
                if (string.matches(".*strDescription\".*")) {
                    String alcoholDescription = string.replaceAll(".*strDescription\"[:][\"]", "");
                    newAlcohol.setDescription(alcoholDescription);
                } else if (string.matches(".*strType.*")) {
                    String alcoholType = string.replaceAll(".*strType\"[:][\"]", "").replace("\"", "");
                    newAlcohol.setAlcoholType(alcoholType);
                }
            }
            alcoholList.add(newAlcohol);
        }
    }


    //create and parse resource url to readable format
    private List<String> getListOfStringFromArrayNode(String resourceURL) {
        List<String> strings = Collections.emptyList();
        ArrayNode arrayNode = getArrayNodeFromJsonNodeBody(getJsonNodeBody(resourceURL));
        for (int i = 0; i < arrayNode.size(); i++) {
            JsonNode arrayElement = arrayNode.get(i);
            strings = Arrays.asList(arrayElement.toString().split("\","));
            break; // to find only first element in case of multiple elements;
        }
        return strings;
    }

    private JsonNode getJsonNodeBody(String resourceURL) {
        ResponseEntity<String> response = new RestTemplate().getForEntity(resourceURL, String.class);
        return prepareJsonNodeBody(response);
    }

    private JsonNode prepareJsonNodeBody(ResponseEntity<String> response) {
        JsonNode root = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            root = mapper.readTree(response.getBody());
        } catch (JsonProcessingException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return root;
    }

    private ArrayNode getArrayNodeFromJsonNodeBody(JsonNode root) {
        ArrayNode arrayNode = null;
        if (root != null) {
            Iterator<String> fieldNames = root.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode fieldValue = root.get(fieldName);
                arrayNode = (ArrayNode) fieldValue;
            }
        }
        return arrayNode;
    }
}
