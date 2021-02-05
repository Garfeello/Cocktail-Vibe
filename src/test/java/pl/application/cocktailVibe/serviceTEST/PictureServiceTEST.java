package pl.application.cocktailVibe.serviceTEST;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.application.cocktailVibe.dto.PictureDTO;
import pl.application.cocktailVibe.services.PictureService;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PictureServiceTEST {

    @Autowired
    private PictureService pictureService;

    @Test
    public void givenImageUrlAndNameWhenCreatePictureThenAssertNotNull() {
        //given
        String url = "https://upload.wikimedia.org/wikipedia/commons/8/82/Facebook_icon.jpg";
        String name = "facebook";
        //when
        PictureDTO pictureFromUrl = pictureService.createPictureFromUrl(url, name);
        //then
        assertNotNull(pictureFromUrl.getImage());
        assertEquals(name + ".jpg", pictureFromUrl.getName());
    }

}
