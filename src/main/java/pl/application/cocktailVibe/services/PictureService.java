package pl.application.cocktailVibe.services;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pl.application.cocktailVibe.dto.PictureDTO;
import pl.application.cocktailVibe.model.Picture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

@Component
public class PictureService {

    public PictureDTO createPictureFromUrl(String pictureURL, String cocktailName) {
        PictureDTO picture = new PictureDTO();
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

    public Picture getPicture(String cocktailName, MultipartFile file) {
        Picture picture = new Picture();
        picture.setName(cocktailName + ".jpg");
        try {
            picture.setImage(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picture;
    }

}
