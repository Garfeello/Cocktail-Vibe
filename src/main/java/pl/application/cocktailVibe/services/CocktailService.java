package pl.application.cocktailVibe.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.application.cocktailVibe.dto.AlcoholDTO;
import pl.application.cocktailVibe.dto.CocktailDTO;
import pl.application.cocktailVibe.dto.IngredientDTO;
import pl.application.cocktailVibe.dto.PictureDTO;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.model.Picture;
import pl.application.cocktailVibe.repository.AlcoholRepository;
import pl.application.cocktailVibe.repository.CocktailRepository;
import pl.application.cocktailVibe.repository.IngredientRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CocktailService {

    private final Logger logger = LoggerFactory.getLogger(CocktailService.class);
    private final CocktailRepository cocktailRepository;
    private final AlcoholRepository alcoholRepository;
    private final IngredientRepository ingredientRepository;

    public CocktailService(CocktailRepository cocktailRepository, AlcoholRepository alcoholRepository, IngredientRepository ingredientRepository) {
        this.cocktailRepository = cocktailRepository;
        this.alcoholRepository = alcoholRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public Cocktail getCocktail(CocktailDTO cocktailDTO) {
        Optional<Cocktail> cocktailOptional = cocktailRepository.findFirstByName(cocktailDTO.getName());
        if (cocktailOptional.isPresent()) {
            return cocktailOptional.get();
        }
        Cocktail cocktail = new Cocktail();
        if (cocktailDTO == null){
            logger.info("Cocktail DTO in cocktailService was null " + LocalDateTime.now());
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
            Optional<Alcohol> optionalAlcohol = alcoholRepository.findFirstByName(alcoholDTO.getName());
            if (optionalAlcohol.isPresent()){
                alcoholList.add(optionalAlcohol.get());
            } else {
                Alcohol alcohol = new Alcohol();
                alcohol.setName(alcoholDTO.getName());
                alcohol.setDescription(alcoholDTO.getDescription());
                alcohol.setAlcoholType(alcoholDTO.getAlcoholType());
                alcohol.setAge(alcoholDTO.getAge());
                alcohol.setPicture(getPicture(alcoholDTO.getPicture()));
                alcohol.setLanguage(alcoholDTO.getLanguage());
                alcoholList.add(alcohol);
            }
        }
        return alcoholList;
    }

    private List<Ingredient> getIngredientList(List<IngredientDTO> ingredientDTOS) {
        List<Ingredient> ingredientList = new ArrayList<>();
        if (ingredientDTOS == null) {
            return ingredientList;
        }
        for (IngredientDTO ingredientDTO : ingredientDTOS) {
            Optional<Ingredient> optionalIngredient = ingredientRepository.findFirstByName(ingredientDTO.getName());
            if (optionalIngredient.isPresent()){
                ingredientList.add(optionalIngredient.get());
            } else {
                Ingredient ingredient = new Ingredient();
                ingredient.setName(ingredientDTO.getName());
                ingredient.setType(ingredientDTO.getType());
                ingredient.setLanguage(ingredientDTO.getLanguage());
                ingredientList.add(ingredient);
            }
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
