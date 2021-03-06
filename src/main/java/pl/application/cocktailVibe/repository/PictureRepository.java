package pl.application.cocktailVibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.application.cocktailVibe.model.Picture;

import java.util.Optional;

public interface PictureRepository extends JpaRepository<Picture, Long>, CrudRepository<Picture, Long> {

    Optional<Picture> findByName(String name);
}
