package pl.application.cocktailVibe.services;

import org.springframework.stereotype.Component;
import pl.application.cocktailVibe.model.Picture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

@Component
public class PictureService {

    public Picture createPictureFromUrl(String pictureURL, String cocktailName) {
        Picture picture = new Picture();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            URL url = new URL(pictureURL);
            BufferedImage image = ImageIO.read(url);
            ImageIO.write(image, "jpg", byteArrayOutputStream);
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        picture.setImage(byteArrayOutputStream.toByteArray());
        picture.setName(cocktailName + ".jpg");
        return picture;
    }

}
