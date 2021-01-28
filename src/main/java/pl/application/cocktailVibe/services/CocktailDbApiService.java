package pl.application.cocktailVibe.services;

import org.springframework.stereotype.Component;
import pl.application.cocktailVibe.apiIntegration.CocktailDbAPI;
import pl.application.cocktailVibe.apiIntegrationModel.DrinkApiModel;
import pl.application.cocktailVibe.apiIntegrationModel.IngredientApiModel;
import pl.application.cocktailVibe.dto.AlcoholDTO;
import pl.application.cocktailVibe.dto.CocktailDTO;
import pl.application.cocktailVibe.dto.IngredientDTO;
import pl.application.cocktailVibe.wrapper.ApiObjectsWrapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class CocktailDbApiService {

    private final CocktailDbAPI cocktailDbAPI;
    private final PictureService pictureService;

    public CocktailDbApiService(CocktailDbAPI cocktailDbAPI, PictureService pictureService) {
        this.cocktailDbAPI = cocktailDbAPI;
        this.pictureService = pictureService;
    }

    public void getCocktailDto(int drinkId) {
        ApiObjectsWrapper apiObjectsWrapper = cocktailDbAPI.getApiObjectFromDrinkId(drinkId);
        CocktailDTO cocktailDTO = prepareCocktailDto(apiObjectsWrapper.getDrinkApiModel(), apiObjectsWrapper.getIngredientApiModelList());
        System.out.println(cocktailDTO.getAlcoholDTOList() + " " + cocktailDTO.getIngredientDTOList() + " " +
                cocktailDTO.getLanguage() + " " + cocktailDTO.getName() + " " + cocktailDTO.getPreparationDescription());
    }

    private CocktailDTO prepareCocktailDto(DrinkApiModel drinkApiModel, List<IngredientApiModel> ingredientApiModels) {
        CocktailDTO cocktailDTO = new CocktailDTO();
        cocktailDTO.setName(drinkApiModel.getStrDrink());
        cocktailDTO.setPreparationDescription(drinkApiModel.getStrInstructions());
        cocktailDTO.setAlcoholDTOList(prepareAlcoholDto(ingredientApiModels));
        cocktailDTO.setIngredientDTOList(prepareIngredientDto(ingredientApiModels));
        cocktailDTO.setPictureDTO(pictureService.createPictureFromUrl(drinkApiModel.getStrDrinkThumb(), drinkApiModel.getStrDrink()));
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
                alcoholDTOList.add(alcoholDTO);
            }
        }
        return alcoholDTOList;
    }

    private List<IngredientDTO> prepareIngredientDto(List<IngredientApiModel> ingredientApiModels) {
        List<IngredientDTO> ingredientDTOList = new ArrayList<>();
        for (IngredientApiModel ingredient : ingredientApiModels) {
            if (ingredient.getStrAlcohol() != null) {
                IngredientDTO ingredientDTO = new IngredientDTO();
                ingredientDTO.setName(ingredient.getStrIngredient());
                ingredientDTO.setType(ingredient.getStrType());
                ingredientDTO.setLanguage("en");
            }
        }
        return ingredientDTOList;
    }
}
