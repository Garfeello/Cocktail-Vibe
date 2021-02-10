package pl.application.cocktailVibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.model.User;

import java.util.List;
import java.util.Optional;

public interface CocktailRepository extends JpaRepository<Cocktail, Long>, CrudRepository<Cocktail, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM cocktail where DATE(created_on) < CURRENT_TIMESTAMP AND language = :language order by created_on desc LIMIT 6;")
    Optional<List<Cocktail>> findSixNewestCocktails(@Param("language") String language);

    @Override
    Optional<Cocktail> findById(Long id);

    Optional<Cocktail> findFirstByName(String cocktailName);

    Optional<Cocktail> findFirstByNameAndLanguage(String cocktailName, String cocktailLanguage);

    Optional<List<Cocktail>> findCocktailsByLanguage(String language);

    Optional<List<Cocktail>> findCocktailsByUserAndLanguage(User user, String language);

    Optional<List<Cocktail>> findCocktailsByIngredients(Ingredient ingredient);

    Optional<List<Cocktail>> findCocktailsByAlcoholList(Alcohol alcohol);

    Optional<List<Cocktail>> findCocktailsByNameAndLanguage(String name, String language);

}
