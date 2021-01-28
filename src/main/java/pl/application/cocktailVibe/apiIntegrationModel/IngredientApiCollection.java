package pl.application.cocktailVibe.apiIntegrationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class IngredientApiCollection {

    @JsonProperty("ingredients")
    private List<IngredientApiModel> ingredientApiModelList;

    public IngredientApiCollection() {
    }

    public List<IngredientApiModel> getIngredientApiModelList() {
        return ingredientApiModelList;
    }

    public void setIngredientApiModelList(List<IngredientApiModel> ingredientApiModelList) {
        this.ingredientApiModelList = ingredientApiModelList;
    }

    @Override
    public String toString() {
        return "IngredientApiCollection{" +
                "ingredientApiModelList=" + ingredientApiModelList +
                '}';
    }
}
