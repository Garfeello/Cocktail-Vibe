package pl.application.cocktailVibe.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Alcohol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "name cant be left blank")
    @Size(min = 2, max = 35)
    private String name;

    private String alcoholType;

    @Max(value = 100, message = "maximum age is 100")
    private int age;

    @Size(max = 5500, message = "max word 5500")
    private String description;

    @NotNull
    private String language;

    @OneToOne(cascade = CascadeType.ALL)
    private Picture picture;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Alcohol{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alcoholType='" + alcoholType + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", picture=" + picture +
                ", user=" + user +
                '}';
    }
}
