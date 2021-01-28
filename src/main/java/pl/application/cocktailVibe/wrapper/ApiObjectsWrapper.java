package pl.application.cocktailVibe.wrapper;

import pl.application.cocktailVibe.apiIntegrationModel.DrinkApiModel;
import pl.application.cocktailVibe.apiIntegrationModel.IngredientApiModel;

import java.util.List;

public class ApiObjectsWrapper {

    private DrinkApiModel drinkApiModel;
    private List<IngredientApiModel> ingredientApiModelList;

    public DrinkApiModel getDrinkApiModel() {
        return drinkApiModel;
    }

    public void setDrinkApiModel(DrinkApiModel drinkApiModel) {
        this.drinkApiModel = drinkApiModel;
    }

    public List<IngredientApiModel> getIngredientApiModelList() {
        return ingredientApiModelList;
    }

    public void setIngredientApiModelList(List<IngredientApiModel> ingredientApiModelList) {
        this.ingredientApiModelList = ingredientApiModelList;
    }

    @Override
    public String toString() {
        return "ApiObjectsWrapper{" +
                "drinkApiModel=" + drinkApiModel +
                ", ingredientApiModelList=" + ingredientApiModelList +
                '}';
    }
}
