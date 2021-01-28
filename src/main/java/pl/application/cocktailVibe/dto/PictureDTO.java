package pl.application.cocktailVibe.dto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;

public class PictureDTO {

    @NotEmpty
    private String name;

    @Lob
    private byte[] image;

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
