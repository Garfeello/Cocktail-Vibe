package pl.application.cocktailVibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.model.User;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
