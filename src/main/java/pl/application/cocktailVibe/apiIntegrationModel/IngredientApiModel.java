package pl.application.cocktailVibe.apiIntegrationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IngredientApiModel {

    private String strIngredient;
    private String strDescription;
    private String strType;
    private String strAlcohol;

    public IngredientApiModel() {
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public String getStrType() {
        return strType;
    }

    public void setStrType(String strType) {
        this.strType = strType;
    }

    public String getStrAlcohol() {
        return strAlcohol;
    }

    public void setStrAlcohol(String strAlcohol) {
        this.strAlcohol = strAlcohol;
    }

    @Override
    public String toString() {
        return "IngredientApiModel{" +
                "strIngredient='" + strIngredient + '\'' +
                ", strDescription='" + strDescription + '\'' +
                ", strType='" + strType + '\'' +
                ", strAlcohol='" + strAlcohol + '\'' +
                '}';
    }
}
