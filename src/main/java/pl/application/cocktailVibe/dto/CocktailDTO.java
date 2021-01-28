package pl.application.cocktailVibe.dto;

import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.model.Picture;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class CocktailDTO {

    @Size(min = 2, max = 40)
    private String name;

    private List<AlcoholDTO> alcoholList;

    private List<IngredientDTO> ingredients;

    @Size(max = 1000, message = "description max characters - 1000")
    private String preparationDescription;

    @Size(max = 200, message = "user inspiration max characters - 500")
    private String userInspiration;

    private Picture picture;

    @NotEmpty
    private String language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AlcoholDTO> getAlcoholList() {
        return alcoholList;
    }

    public void setAlcoholList(List<AlcoholDTO> alcoholList) {
        this.alcoholList = alcoholList;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparationDescription() {
        return preparationDescription;
    }

    public void setPreparationDescription(String preparationDescription) {
        this.preparationDescription = preparationDescription;
    }

    public String getUserInspiration() {
        return userInspiration;
    }

    public void setUserInspiration(String userInspiration) {
        this.userInspiration = userInspiration;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "CocktailDTO{" +
                "name='" + name + '\'' +
                ", alcoholList=" + alcoholList +
                ", ingredients=" + ingredients +
                ", preparationDescription='" + preparationDescription + '\'' +
                ", userInspiration='" + userInspiration + '\'' +
                ", picture=" + picture +
                ", language='" + language + '\'' +
                '}';
    }
}
