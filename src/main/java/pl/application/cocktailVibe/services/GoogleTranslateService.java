package pl.application.cocktailVibe.services;

import org.springframework.stereotype.Service;
import pl.application.cocktailVibe.apiIntegration.GoogleTranslateAPI;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.repository.AlcoholRepository;
import pl.application.cocktailVibe.repository.CocktailRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GoogleTranslateService {

    private final GoogleTranslateAPI googleTranslateAPI;
    private final CocktailRepository cocktailRepository;
    private final AlcoholRepository alcoholRepository;

    public GoogleTranslateService(GoogleTranslateAPI googleTranslateAPI, CocktailRepository cocktailRepository,
                                  AlcoholRepository alcoholRepository) {
        this.googleTranslateAPI = googleTranslateAPI;
        this.cocktailRepository = cocktailRepository;
        this.alcoholRepository = alcoholRepository;
    }

    public Cocktail translateAngGetCocktail(String cocktailName, String translatedFrom, String translatedTo) {
        Optional<Cocktail> cocktailOptional = cocktailRepository.findFirstByNameAndLanguage(cocktailName, translatedTo);
        if (cocktailOptional.isEmpty()) {
            cocktailOptional = cocktailRepository.findFirstByName(cocktailName);
        } else {
            return cocktailOptional.get();
        }
        return googleTranslateAPI.translateCocktail(cocktailOptional.get(), translatedFrom, translatedTo);
    }

    public Alcohol translateAndGetAlcohol(String alcoholName, String translatedTo) {
        Optional<Alcohol> alcoholOptional = alcoholRepository.findFirstByNameAndLanguage(alcoholName, translatedTo);
        if (alcoholOptional.isEmpty()) {
            alcoholOptional = alcoholRepository.findFirstByName(alcoholName);
        } else {
            return alcoholOptional.get();
        }
        return googleTranslateAPI.translateAlcohols(List.of(alcoholOptional.get()), translatedTo).get(0);
    }

}
