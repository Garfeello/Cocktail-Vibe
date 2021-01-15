package pl.application.cocktailVibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.application.cocktailVibe.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>, CrudRepository<Ingredient, Long> {

    @Query("select i from Ingredient i")
    List<Ingredient> findAllIngredients();

    Optional<Ingredient> findById(Long ingredientId);
}
