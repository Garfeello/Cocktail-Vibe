package pl.application.cocktailVibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.application.cocktailVibe.model.Alcohol;

import java.util.List;
import java.util.Optional;

public interface AlcoholRepository extends JpaRepository<Alcohol, Long>, CrudRepository<Alcohol, Long> {

    @Query("select a from Alcohol a")
    List<Alcohol> findAllAlcohols();

    Optional<Alcohol> findById(Long longId);

    Optional<Alcohol> findFirstByName(String name);

}
