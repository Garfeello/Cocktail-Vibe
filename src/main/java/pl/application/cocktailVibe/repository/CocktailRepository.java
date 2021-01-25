package pl.application.cocktailVibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.model.User;

import java.util.List;
import java.util.Optional;

public interface CocktailRepository extends JpaRepository<Cocktail, Long>, CrudRepository<Cocktail, Long> {

    @Query("select c from Cocktail c")
    List<Cocktail> findAllCocktails();

    List<Cocktail> findCocktailsByLanguage(String language);

    @Query(nativeQuery = true, value = "SELECT * FROM cocktail where DATE(created_on) < CURRENT_TIMESTAMP order by created_on desc LIMIT 5;")
    List<Cocktail> findFiveNewestCocktails();

    Optional<Cocktail> findFirstByNameAndLanguage(String cocktailName, String cocktailLanguage);

    Optional<Cocktail> findFirstByName(String cocktailName);

    Optional<List<Cocktail>> findCocktailsByIngredients(Ingredient ingredient);

    Optional<List<Cocktail>> findCocktailsByAlcoholList(Alcohol alcohol);

    Optional<List<Cocktail>> findCocktailsByUser(User user);

}
