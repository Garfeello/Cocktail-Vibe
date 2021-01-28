package pl.application.cocktailVibe.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class CocktailDTO {

    @Size(min = 2, max = 40)
    private String name;

    private List<AlcoholDTO> alcoholDTOList;

    private List<IngredientDTO> ingredientDTOList;

    @Size(max = 1000, message = "description max characters - 1000")
    private String preparationDescription;

    @Size(max = 200, message = "user inspiration max characters - 500")
    private String userInspiration;

    private PictureDTO pictureDTO;

    @NotEmpty
    private String language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AlcoholDTO> getAlcoholDTOList() {
        return alcoholDTOList;
    }

    public void setAlcoholDTOList(List<AlcoholDTO> alcoholDTOList) {
        this.alcoholDTOList = alcoholDTOList;
    }

    public List<IngredientDTO> getIngredientDTOList() {
        return ingredientDTOList;
    }

    public void setIngredientDTOList(List<IngredientDTO> ingredientDTOList) {
        this.ingredientDTOList = ingredientDTOList;
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

    public PictureDTO getPictureDTO() {
        return pictureDTO;
    }

    public void setPictureDTO(PictureDTO pictureDTO) {
        this.pictureDTO = pictureDTO;
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
                ", alcoholList=" + alcoholDTOList +
                ", ingredients=" + ingredientDTOList +
                ", preparationDescription='" + preparationDescription + '\'' +
                ", userInspiration='" + userInspiration + '\'' +
                ", picture=" + pictureDTO +
                ", language='" + language + '\'' +
                '}';
    }
}
