package pl.application.cocktailVibe.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 40)
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Alcohol> alcoholList;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Ingredient> ingredients;

    @Size(max = 1000, message = "description max characters - 1000")
    private String preparationDescription;

    @Size(max = 200, message = "user inspiration max characters - 500")
    private String userInspiration;

    @OneToOne(cascade = CascadeType.ALL)
    private Picture picture;

    @NotEmpty
    private String language;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

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

    public List<Alcohol> getAlcoholList() {
        return alcoholList;
    }

    public void setAlcoholList(List<Alcohol> alcoholList) {
        this.alcoholList = alcoholList;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparationDescription() {
        return preparationDescription;
    }

    public void setPreparationDescription(String preparationDescription) {
        this.preparationDescription = preparationDescription;
    }

    public String getUserInspiration() {
        return userInspiration;
    }

    public void setUserInspiration(String userInspiration) {
        this.userInspiration = userInspiration;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    @PrePersist
    public void setCreatedOn() {
        this.createdOn = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Cocktail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alcoholList=" + alcoholList +
                ", ingredients=" + ingredients +
                ", preparationDescription='" + preparationDescription + '\'' +
                ", userInspiration='" + userInspiration + '\'' +
                ", picture=" + picture +
                ", language='" + language + '\'' +
                ", createdOn=" + createdOn +
                ", user=" + user +
                '}';
    }
}
