package pl.application.cocktailVibe.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.repository.IngredientRepository;

@Component
public class IngredientConverter implements Converter<String, Ingredient> {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public Ingredient convert(String source) {
        return ingredientRepository.findById(Long.parseLong(source)).orElse(new Ingredient());
    }
}
