package pl.application.cocktailVibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.application.cocktailVibe.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>, CrudRepository<Ingredient, Long> {

    Optional<Ingredient> findById(Long ingredientId);

    Optional<Ingredient> findFirstByName(String ingredientName);

    Optional<Ingredient> findFirstByNameAndLanguage(String name, String language);

    Optional<List<Ingredient>> findAllByLanguage(String language);
}
