package pl.application.cocktailVibe.dto;

import pl.application.cocktailVibe.model.Picture;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AlcoholDTO {

    @Size(min = 2, max = 35)
    private String name;

    private String alcoholType;

    @Max(value = 100, message = "maximum age is 100")
    private int age;

    @Size(max = 5000, message = "max word 5000")
    private String description;

    @NotNull
    private String language;

    private PictureDTO picture;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlcoholType() {
        return alcoholType;
    }

    public void setAlcoholType(String alcoholType) {
        this.alcoholType = alcoholType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public PictureDTO getPicture() {
        return picture;
    }

    public void setPicture(PictureDTO picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "AlcoholDTO{" +
                "name='" + name + '\'' +
                ", alcoholType='" + alcoholType + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", picture=" + picture +
                '}';
    }
}
