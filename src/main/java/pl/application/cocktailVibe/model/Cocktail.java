package pl.application.cocktailVibe.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 25)
    private String name;

    @NotEmpty
    @ManyToMany
    private List<Alcohol> alcoholList;

    @OneToMany
    private List<Ingredient> ingredients;

    @Size(max = 500)
    private String prepDescription;

    @Size(max = 500)
    private String userInspiration;

    @OneToOne
    private Picture picture;



}
