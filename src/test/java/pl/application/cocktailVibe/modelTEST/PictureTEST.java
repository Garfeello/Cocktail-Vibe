package pl.application.cocktailVibe.modelTEST;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Picture;
import pl.application.cocktailVibe.model.User;
import pl.application.cocktailVibe.repository.AlcoholRepository;
import pl.application.cocktailVibe.repository.PictureRepository;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PictureTEST {

    @Autowired
    private PictureRepository pictureRepository;

    @Test
    @Transactional
    public void givenPictureWhenFindPictureThenPictureSaveCorrectly() {
        //given
        Picture picture = new Picture();
        picture.setId(500L);
        picture.setName("FACEBOOK");
        picture.setImage(new byte[0]);
        Long id = pictureRepository.save(picture).getId();
        //when
        Picture found = pictureRepository.findById(id).orElse(new Picture());
        //then
        assertSame(picture.getName(), found.getName());
        assertNotSame(picture.getId(), found.getId());
    }

    @Test
    public void givenWrongPictureIdWhenFindPictureByIDThenAssertNotNullObjectWithNullValues(){
        //given
        Picture found = pictureRepository.findById(0L).orElse(new Picture());
        //when
        Picture picture = new Picture();
        picture.setImage(found.getImage());
        picture.setName(found.getName());
        //then
        assertNotNull(found);
        assertNull(picture.getImage());
        assertNull(picture.getName());
    }

}
