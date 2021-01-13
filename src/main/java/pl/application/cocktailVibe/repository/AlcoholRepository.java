package pl.application.cocktailVibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.application.cocktailVibe.model.Alcohol;

import java.util.List;

public interface AlcoholRepository extends JpaRepository<Alcohol, Long>, CrudRepository<Alcohol, Long> {

    @Query("select a from Alcohol a")
    List<Alcohol> findAllAlcohols();


}
