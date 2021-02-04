package pl.application.cocktailVibe.dtoTEST;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.application.cocktailVibe.dto.UserDTO;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.repository.AlcoholRepository;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

public class SimpleAlcoholTest {

    @Test
    public void createUserAndCheckIfCorrectData() {
        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("");
        userDTO.setPassword("ABCdsadasdas12312");
        userDTO.setNickName("TEST");
        userDTO.setMatchingPassword("ABCdsadasdas12312");
        //when



        //then
//        assertSame(userDTO.getEmail(), found.getName());
//        assertNotSame(alcohol.getId(), found.getId());
    }


}
