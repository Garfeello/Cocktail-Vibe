package pl.application.cocktailVibe.modelTEST;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.repository.CocktailRepository;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SimpleCocktailTest {

    @Autowired
    private CocktailRepository cocktailRepository;

    @Test
    @Transactional
    public void givenCocktailWhenFindThenUserSaveCorrectly() {
        //given
        Cocktail cocktail = new Cocktail();
        cocktail.setLanguage("Pl");
        cocktail.setPreparationDescription("TESTTESTTESTTESTTEST");
        cocktail.setName("Rémy Martin");
        cocktail.setLanguage("pl");
        cocktailRepository.save(cocktail);
        //when
        Cocktail found = cocktailRepository.findFirstByName("Rémy Martin").orElse(new Cocktail());
        //then
        assertSame(cocktail.getName(), found.getName());
        assertNotSame(cocktail.getId(), found.getId());
    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional
    public void givenCocktailWhenSaveThenThrownConstraintViolationException() {
        //given
        Cocktail cocktail = new Cocktail();
        cocktail.setLanguage("Pl");
        cocktail.setPreparationDescription("TESTTESTTESTTESTTEST");
        cocktail.setName(null);
        cocktail.setLanguage("pl");
        cocktailRepository.save(cocktail);
    }

    @Test
    public void givenWrongNullCocktailWhenFindCocktailByNameThenAssertNotNullObjectWithNullValues(){
        //given
        Cocktail found = cocktailRepository.findFirstByName(null).orElse(new Cocktail());
        //when
        Cocktail cocktail = new Cocktail();
        cocktail.setName(found.getName());
        cocktail.setAlcoholList(found.getAlcoholList());
        //then
        assertNotNull(found);
        assertNull(cocktail.getAlcoholList());
        assertNull(cocktail.getName());
    }
}
