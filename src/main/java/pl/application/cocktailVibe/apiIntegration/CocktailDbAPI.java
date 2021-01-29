package pl.application.cocktailVibe.apiIntegration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import pl.application.cocktailVibe.apiIntegrationModel.DrinkApiCollection;
import pl.application.cocktailVibe.apiIntegrationModel.DrinkApiModel;
import pl.application.cocktailVibe.apiIntegrationModel.IngredientApiCollection;
import pl.application.cocktailVibe.apiIntegrationModel.IngredientApiModel;
import pl.application.cocktailVibe.wrapper.ApiObjectsWrapper;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CocktailDbAPI {

    private final ObjectMapper objectMapper;

    public CocktailDbAPI(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ApiObjectsWrapper getApiObjectFromDrinkId() {
        DrinkApiModel drinkApiModel = getDrinkApiModelById();
        if (drinkApiModel.getStrDrink() == null){
            return new ApiObjectsWrapper();
        }

        List<IngredientApiModel> ingredientApiModelList = getIngredientModels(drinkApiModel);

        ApiObjectsWrapper apiObjectsWrapper = new ApiObjectsWrapper();
        apiObjectsWrapper.setDrinkApiModel(drinkApiModel);
        apiObjectsWrapper.setIngredientApiModelList(ingredientApiModelList);
        return apiObjectsWrapper;
    }

    private List<IngredientApiModel> getIngredientModels(DrinkApiModel drinkApiModel) {
        List<String> ingredientList = getIngredientList(drinkApiModel);
        List<IngredientApiModel> ingredientApiModels = new ArrayList<>();
        for (String ingredientName : ingredientList) {
            ingredientApiModels.add(getIngredientAPiModelByName(ingredientName));
        }
        return ingredientApiModels;
    }

    private List<String> getIngredientList(DrinkApiModel drinkApiModel) {
        List<String> ingredientList = new ArrayList<>();
        Collections.addAll(ingredientList, drinkApiModel.getStrIngredient1(), drinkApiModel.getStrIngredient2(),
                drinkApiModel.getStrIngredient3(), drinkApiModel.getStrIngredient4(), drinkApiModel.getStrIngredient5(),
                drinkApiModel.getStrIngredient6(), drinkApiModel.getStrIngredient7(), drinkApiModel.getStrIngredient8(),
                drinkApiModel.getStrIngredient9(), drinkApiModel.getStrIngredient10());
        return filterNullFromIngredientList(ingredientList);
    }

    private List<String> filterNullFromIngredientList(List<String> ingredientList) {
        return ingredientList.stream().filter(x -> !(x == null)).collect(Collectors.toList());
    }

    private IngredientApiModel getIngredientAPiModelByName(String ingredientName) {

        String resourceURL = "https://www.thecocktaildb.com/api/json/v1/1/search.php?i=" + ingredientName.replace(" ", "%20");
        IngredientApiModel ingredientApiModel = new IngredientApiModel();

        try {
            IngredientApiCollection ingredientApiCollection = objectMapper.readValue(new URL(resourceURL), IngredientApiCollection.class);
            ingredientApiModel = ingredientApiCollection.getIngredientApiModelList().get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingredientApiModel;
    }

    private DrinkApiModel getDrinkApiModelById() {
        String resourceURL = "https://www.thecocktaildb.com/api/json/v1/1/random.php";
        Optional<List<DrinkApiModel>> drinkApiModel = Optional.empty();
        try {
            DrinkApiCollection drinkApiCollection = objectMapper.readValue(new URL(resourceURL), DrinkApiCollection.class);
            drinkApiModel = Optional.ofNullable(drinkApiCollection.getDrinksList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (drinkApiModel.isPresent()){
            return drinkApiModel.get().get(0);
        } else {
            return new DrinkApiModel();
        }
    }
}
