package pl.application.cocktailVibe.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class IngredientDTO {

    @NotEmpty
    @Size(min = 2, max = 25)
    @Column(unique = true)
    private String name;

    private String type;

    private String language;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "IngredientDTO{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
