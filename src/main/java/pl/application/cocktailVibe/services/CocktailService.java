package pl.application.cocktailVibe.services;

import org.springframework.stereotype.Component;
import pl.application.cocktailVibe.dto.AlcoholDTO;
import pl.application.cocktailVibe.dto.CocktailDTO;
import pl.application.cocktailVibe.dto.IngredientDTO;
import pl.application.cocktailVibe.dto.PictureDTO;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.model.Picture;
import pl.application.cocktailVibe.repository.CocktailRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class CocktailService {

    private final CocktailRepository cocktailRepository;

    public CocktailService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    public Cocktail getCocktail(CocktailDTO cocktailDTO) {
        Optional<Cocktail> cocktailOptional = cocktailRepository.findFirstByName(cocktailDTO.getName());
        if (cocktailOptional.isPresent()) {
            return cocktailOptional.get();
        }
        Cocktail cocktail = new Cocktail();
        if (cocktailDTO == null){
            return cocktail;
        }
        cocktail.setName(cocktailDTO.getName());
        cocktail.setPreparationDescription(cocktailDTO.getPreparationDescription());
        cocktail.setAlcoholList(getAlcoholList(cocktailDTO.getAlcoholDTOList()));
        cocktail.setIngredients(getIngredientList(cocktailDTO.getIngredientDTOList()));
        cocktail.setLanguage(cocktailDTO.getLanguage());
        cocktail.setPicture(getPicture(cocktailDTO.getPictureDTO()));
        return cocktail;
    }


    private List<Alcohol> getAlcoholList(List<AlcoholDTO> alcoholDTOS) {
        List<Alcohol> alcoholList = new ArrayList<>();
        if (alcoholDTOS == null) {
            return alcoholList;
        }
        for (AlcoholDTO alcoholDTO : alcoholDTOS) {
            Alcohol alcohol = new Alcohol();
            alcohol.setName(alcoholDTO.getName());
            alcohol.setDescription(alcoholDTO.getDescription());
            alcohol.setAlcoholType(alcoholDTO.getAlcoholType());
            alcohol.setAge(alcoholDTO.getAge());
            alcohol.setPicture(alcoholDTO.getPicture());
            alcohol.setLanguage(alcoholDTO.getLanguage());
            alcoholList.add(alcohol);
        }
        return alcoholList;
    }

    private List<Ingredient> getIngredientList(List<IngredientDTO> ingredientDTOS) {
        List<Ingredient> ingredientList = new ArrayList<>();
        if (ingredientDTOS == null) {
            return ingredientList;
        }
        for (IngredientDTO ingredientDTO : ingredientDTOS) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(ingredientDTO.getName());
            ingredient.setType(ingredientDTO.getType());
            ingredient.setLanguage(ingredientDTO.getLanguage());
            ingredientList.add(ingredient);
        }
        return ingredientList;
    }

    private Picture getPicture(PictureDTO pictureDTO) {
        Picture picture = new Picture();
        if (pictureDTO == null){
            return picture;
        }
        picture.setImage(pictureDTO.getImage());
        picture.setName(pictureDTO.getName());
        return picture;
    }
}
