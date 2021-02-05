package pl.application.cocktailVibe.modelTEST;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import pl.application.cocktailVibe.model.User;
import pl.application.cocktailVibe.repository.UserRepository;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SimpleUserTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setupUniqueEmailException() {
        User user = new User();
        user.setEmail("TEST@TEST");
        userRepository.save(user);
    }

    @Test
    @Transactional
    public void givenUserWhenFindThenUserSaveCorrectly() {
        //given
        User user = new User();
        user.setId(1L);
        user.setRole("ROLE_USER");
        user.setNickName("TEST");
        user.setPassword("12345");
        userRepository.save(user);
        //when
        User found = userRepository.findById(1L).orElse(new User());
        //then
        assertSame(user.getNickName(), found.getNickName());
        assertSame(user.getId(), found.getId());
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    public void givenUserWhenSaveThenThrownConstraintViolationException() {
        //given
        User user = new User();
        user.setNickName("TEST2");
        user.setRole("ROLE_USER");
        user.setEmail("TEST@TEST");
        //when
        userRepository.save(user);
        //then
    }

    @Test
    public void givenWrongUserIdWhenFindUserByIDThenAssertNotNullObjectWithNullValues() {
        //given
        User found = userRepository.findById(0L).orElse(new User());
        //when
        User user = new User();
        user.setNickName(found.getNickName());
        user.setNickName(found.getNickName());
        //then
        assertNotNull(found);
        assertNull(user.getRole());
        assertNull(user.getNickName());
    }
}
