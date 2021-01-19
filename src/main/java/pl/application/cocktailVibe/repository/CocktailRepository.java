package pl.application.cocktailVibe.repository;

import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface CocktailRepository extends JpaRepository<Cocktail, Long>, CrudRepository<Cocktail, Long> {

    @Query("select c from Cocktail c")
    List<Cocktail> findAllCocktails();

    @Query(nativeQuery = true, value = "SELECT * FROM cocktail where DATE(created_on) < CURRENT_TIMESTAMP order by created_on desc LIMIT 5;")
    List<Cocktail> findFiveNewestCocktails();

    Optional<Cocktail> findFirstByName(String cocktailName);

    List<Cocktail> findCocktailsByIngredients(Ingredient ingredient);

}
