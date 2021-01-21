package pl.application.cocktailVibe.services;

import org.springframework.stereotype.Component;
import pl.application.cocktailVibe.apiIntegration.GoogleTranslateAPI;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.repository.CocktailRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class GoogleTranslateService {

    private final GoogleTranslateAPI googleTranslateAPI;
    private final CocktailRepository cocktailRepository;

    public GoogleTranslateService(GoogleTranslateAPI googleTranslateAPI, CocktailRepository cocktailRepository) {
        this.googleTranslateAPI = googleTranslateAPI;
        this.cocktailRepository = cocktailRepository;
    }

    public Cocktail translateAngGetCocktail(String cocktailName) {
        Optional<Cocktail> optionalCocktail = cocktailRepository.findFirstByNameAndLanguage(cocktailName, "Pl");
        if (optionalCocktail.isEmpty()) {
            optionalCocktail = cocktailRepository.findFirstByName(cocktailName);
            optionalCocktail.ifPresent(this::translateCocktail);
            optionalCocktail = cocktailRepository.findFirstByNameAndLanguage(cocktailName, "Pl");
        }
        return optionalCocktail.get();
    }

    private void translateCocktail(Cocktail cocktail) {
        try {
            googleTranslateAPI.translateAndSaveCocktail(cocktail);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}
