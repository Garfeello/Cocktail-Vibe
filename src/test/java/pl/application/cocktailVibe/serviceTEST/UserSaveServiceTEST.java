package pl.application.cocktailVibe.serviceTEST;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.application.cocktailVibe.dto.UserDTO;
import pl.application.cocktailVibe.model.User;
import pl.application.cocktailVibe.repository.UserRepository;
import pl.application.cocktailVibe.services.UserSaveService;

import javax.transaction.Transactional;
import java.util.Optional;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserSaveServiceTEST {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSaveService userSaveService;

    @Before
    @Transactional
    public void prepareUserDTO(){
        UserDTO userDTO = new UserDTO();
        userDTO.setNickName("TEST1234");
        userDTO.setPassword("TEST123");
        userSaveService.registerNewUserAccount(userDTO);
    }

    @Test
    public void givenDtoUserWhenSaveUserThenCheckPasswordHashAndNotNull(){
        //given
        Optional<User> optionalUser = userRepository.findFirstByNickName("TEST1234");
        //when
        User found = optionalUser.get();
        //then
        assertNotNull(found);
        assertNull(found.getEmail());
        assertNotEquals("TEST123", found.getPassword());

    }
}
