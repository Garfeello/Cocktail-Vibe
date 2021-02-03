package pl.application.cocktailVibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.User;

import java.util.List;
import java.util.Optional;

public interface AlcoholRepository extends JpaRepository<Alcohol, Long>, CrudRepository<Alcohol, Long> {

    @Override
    Optional<Alcohol> findById(Long longId);

    Optional<List<Alcohol>> findAlcoholByLanguage(String language);

    Optional<Alcohol> findFirstByNameAndLanguage(String name, String language);

    Optional<Alcohol> findFirstByName(String name);

    Optional<List<Alcohol>> findAllByUserAndLanguage(User user, String language);


}
