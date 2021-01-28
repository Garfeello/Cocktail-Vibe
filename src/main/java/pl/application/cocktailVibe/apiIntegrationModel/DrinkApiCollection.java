package pl.application.cocktailVibe.apiIntegrationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.List;

public class DrinkApiCollection {

    @JsonProperty("drinks")
    List<DrinkApiModel> drinkApiModelList;

    public DrinkApiCollection() {
    }

    public List<DrinkApiModel> getDrinksList() {
        return drinkApiModelList;
    }

    public void setDrinksList(List<DrinkApiModel> drinkApiModelList) {
        this.drinkApiModelList = drinkApiModelList;
    }

    @Override
    public String toString() {
        return "DrinkApiCollection{" +
                "drinkApiModelList=" + drinkApiModelList +
                '}';
    }
}
