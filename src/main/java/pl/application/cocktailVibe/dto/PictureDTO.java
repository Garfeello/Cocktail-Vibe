package pl.application.cocktailVibe.dto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;

public class PictureDTO {

    @NotEmpty
    private String name;

    @Lob
    private byte[] image;

    private boolean pictureLoaded;

    public boolean isPictureLoaded() {
        return image != null;
    }

    public void setPictureLoaded(boolean pictureLoaded) {
        this.pictureLoaded = pictureLoaded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "PictureDTO{" +
                "name='" + name + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
