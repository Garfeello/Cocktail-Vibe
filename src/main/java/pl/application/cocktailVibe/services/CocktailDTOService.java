package pl.application.cocktailVibe.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.application.cocktailVibe.apiIntegration.CocktailDbAPI;
import pl.application.cocktailVibe.apiIntegrationModel.DrinkApiModel;
import pl.application.cocktailVibe.apiIntegrationModel.IngredientApiModel;
import pl.application.cocktailVibe.dto.AlcoholDTO;
import pl.application.cocktailVibe.dto.CocktailDTO;
import pl.application.cocktailVibe.dto.IngredientDTO;
import pl.application.cocktailVibe.wrapper.ApiObjectsWrapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class CocktailDTOService {

    private final Logger logger = LoggerFactory.getLogger(CocktailDTO.class);
    private final CocktailDbAPI cocktailDbAPI;
    private final PictureService pictureService;

    public CocktailDTOService(CocktailDbAPI cocktailDbAPI, PictureService pictureService) {
        this.cocktailDbAPI = cocktailDbAPI;
        this.pictureService = pictureService;
    }

    public CocktailDTO getCocktailDto(String cocktailName) {
       ApiObjectsWrapper objectsWrapper  = cocktailDbAPI.getApiObjectFromDrinkName(cocktailName);
       if (objectsWrapper.getDrinkApiModel() == null){
           logger.info("Drink API model in CocktailDTOService was null " + LocalDateTime.now());
           return new CocktailDTO();
       } else {
           return prepareCocktailDto(objectsWrapper.getDrinkApiModel(), objectsWrapper.getIngredientApiModelList());
       }
    }

    private CocktailDTO prepareCocktailDto(DrinkApiModel drinkApiModel, List<IngredientApiModel> ingredientApiModels) {
        CocktailDTO cocktailDTO = new CocktailDTO();
        cocktailDTO.setName(drinkApiModel.getStrDrink());
        cocktailDTO.setPreparationDescription(drinkApiModel.getStrInstructions());
        cocktailDTO.setAlcoholDTOList(prepareAlcoholDto(ingredientApiModels));
        cocktailDTO.setIngredientDTOList(prepareIngredientDto(ingredientApiModels));
        cocktailDTO.setPictureDTO(pictureService.createPictureFromUrl(drinkApiModel.getStrDrinkThumb(), drinkApiModel.getStrDrink(), "jpg"));
        cocktailDTO.setLanguage("en");
        return cocktailDTO;
    }

    private List<AlcoholDTO> prepareAlcoholDto(List<IngredientApiModel> ingredientApiModels) {
        List<AlcoholDTO> alcoholDTOList = new ArrayList<>();
        for (IngredientApiModel ingredient : ingredientApiModels) {
            if (ingredient.getStrAlcohol() != null && ingredient.getStrAlcohol().equals("Yes")) {
                AlcoholDTO alcoholDTO = new AlcoholDTO();
                alcoholDTO.setName(ingredient.getStrIngredient());
                alcoholDTO.setDescription(ingredient.getStrDescription());
                alcoholDTO.setAlcoholType(ingredient.getStrType());
                alcoholDTO.setAge(0);
                alcoholDTO.setLanguage("en");
                alcoholDTO.setPicture(pictureService.createPictureFromUrl("https://www.thecocktaildb.com/images/ingredients/" + ingredient.getStrIngredient() + ".png", ingredient.getStrIngredient(), "png"));
                alcoholDTOList.add(alcoholDTO);
            }
        }
        return alcoholDTOList;
    }

    private List<IngredientDTO> prepareIngredientDto(List<IngredientApiModel> ingredientApiModels) {
        List<IngredientDTO> ingredientDTOList = new ArrayList<>();
        for (IngredientApiModel ingredient : ingredientApiModels) {
            if (ingredient.getStrAlcohol() == null) {
                IngredientDTO ingredientDTO = new IngredientDTO();
                ingredientDTO.setName(ingredient.getStrIngredient());
                ingredientDTO.setType(ingredient.getStrType());
                ingredientDTO.setLanguage("en");
                ingredientDTOList.add(ingredientDTO);
            }
        }
        return ingredientDTOList;
    }
}
