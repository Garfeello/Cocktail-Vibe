package pl.application.cocktailVibe.modelTEST;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.application.cocktailVibe.model.Alcohol;
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
    public void createAlcoholAndThenSave() {
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
    public void createAlcoholAndCheckConstraints() {
        //given
        Alcohol alcohol = new Alcohol();
        alcohol.setId(0L);
        alcohol.setAge(10);
        alcohol.setAlcoholType("");
        alcohol.setDescription("dsa");
        alcohol.setName(null);
        alcohol.setLanguage(null);
        alcoholRepository.save(alcohol);
        //when

        //then

    }


}
