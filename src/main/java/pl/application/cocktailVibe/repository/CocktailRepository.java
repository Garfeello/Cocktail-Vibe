package pl.application.cocktailVibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Cocktail;

import java.util.List;

public interface CocktailRepository extends JpaRepository<Cocktail, Long>, CrudRepository<Cocktail, Long> {

    @Query("select c from Cocktail c")
    List<Cocktail> findAllCocktails();
}
