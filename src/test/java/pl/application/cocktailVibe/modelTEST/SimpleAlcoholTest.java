package pl.application.cocktailVibe.modelTEST;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.User;
import pl.application.cocktailVibe.repository.AlcoholRepository;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SimpleAlcoholTest {

    @Autowired
    private AlcoholRepository alcoholRepository;

    @Test
    @Transactional
    public void givenAlcoholWhenFindThenAlcoholSaveCorrectly() {
        //given
        Alcohol alcohol = new Alcohol();
        alcohol.setId(0L);
        alcohol.setAge(10);
        alcohol.setAlcoholType("abc");
        alcohol.setDescription("test desc");
        alcohol.setName("Rémy Martin");
        alcohol.setLanguage("pl");
        alcoholRepository.save(alcohol);
        //when
        Alcohol found = alcoholRepository.findFirstByName("Rémy Martin").orElse(new Alcohol());
        //then
        assertSame(alcohol.getName(), found.getName());
        assertNotSame(alcohol.getId(), found.getId());
    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional
    public void givenAlcoholWhenSaveThenThrownConstraintViolationException() {
        //given
        Alcohol alcohol = new Alcohol();
        alcohol.setId(0L);
        alcohol.setAge(10);
        alcohol.setAlcoholType("");
        alcohol.setDescription("dsa");
        alcohol.setName(null);
        alcohol.setLanguage(null);
        //when
        alcoholRepository.save(alcohol);
        //then
    }

    @Test
    public void givenWrongAlcoholIdWhenFindAlcoholByIDThenAssertNotNullObjectWithNullValues(){
        //given
        Alcohol found = alcoholRepository.findById(0L).orElse(new Alcohol());
        //when
        Alcohol alcohol = new Alcohol();
        alcohol.setName(found.getName());
        alcohol.setDescription(found.getDescription());
        //then
        assertNotNull(found);
        assertNull(alcohol.getDescription());
        assertNull(alcohol.getName());
    }
}
