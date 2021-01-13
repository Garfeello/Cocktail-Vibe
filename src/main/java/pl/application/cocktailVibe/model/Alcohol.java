package pl.application.cocktailVibe.model;

import pl.application.cocktailVibe.enums.AlcoholType;
import pl.application.cocktailVibe.enums.Language;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.persistence.EnumType.STRING;

@Entity
public class Alcohol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 25)
    private String name;

    @NotNull
    @Enumerated(STRING)
    private AlcoholType alcoholType;

    @Max(100)
    private int age;

    @Size(max = 250)
    @Column(columnDefinition = "String default brak opisu/no description")
    private String description;

    @NotNull
    @Enumerated(STRING)
    private Language language;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AlcoholType getAlcoholType() {
        return alcoholType;
    }

    public void setAlcoholType(AlcoholType alcoholType) {
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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Alcohol{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alcoholType=" + alcoholType +
                ", age=" + age +
                ", description='" + description + '\'' +
                ", language=" + language +
                '}';
    }
}