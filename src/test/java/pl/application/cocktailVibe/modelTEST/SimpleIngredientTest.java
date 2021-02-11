package pl.application.cocktailVibe.modelTEST;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.repository.CocktailRepository;
import pl.application.cocktailVibe.repository.IngredientRepository;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SimpleIngredientTest {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    @Transactional
    public void givenIngredientWhenFindThenIngredientSaveCorrectly() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(0l);
        ingredient.setLanguage("pl");
        ingredient.setName("Sugar");
        ingredientRepository.save(ingredient);
        //when
        Ingredient found = ingredientRepository.findFirstByName("Sugar").orElse(new Ingredient());
        //then
        assertEquals(ingredient.getName(), found.getName());
        assertNotSame(ingredient.getId(), found.getId());
    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional
    public void givenIngredientWhenSaveThenThrownConstraintViolationException() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setName(null);
        ingredient.setLanguage("pl");
        ingredientRepository.save(ingredient);
    }

    @Test
    public void givenWrongIngredientNameWhenFindIngredientByNameThenAssertNotNullObjectWithNullValues() {
        //given
        Ingredient found = ingredientRepository.findFirstByName(null).orElse(new Ingredient());
        //when
        Ingredient ingredient = new Ingredient();
        ingredient.setName(found.getName());
        ingredient.setName(found.getName());
        //then
        assertNotNull(found);
        assertNull(ingredient.getLanguage());
        assertNull(ingredient.getName());
    }
}
