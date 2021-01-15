package pl.application.cocktailVibe.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.repository.AlcoholRepository;

@Component
public class AlcoholConverter implements Converter<String, Alcohol> {

    @Autowired
    private AlcoholRepository alcoholRepository;

    @Override
    public Alcohol convert(String source) {
        return alcoholRepository.findById(Long.parseLong(source)).orElse(new Alcohol());
    }
}
