package pl.application.cocktailVibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.application.cocktailVibe.dto.CocktailDTO;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.repository.CocktailRepository;
import pl.application.cocktailVibe.services.CocktailDbApiService;
import pl.application.cocktailVibe.services.CocktailService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cocktailVibe/adminPanel")
public class AdminPanelController {

    private final CocktailDbApiService cocktailDbApiService;
    private final CocktailService cocktailService;
    private final CocktailRepository cocktailRepository;

    public AdminPanelController(CocktailDbApiService cocktailDbApiService, CocktailService cocktailService,
                                CocktailRepository cocktailRepository) {
        this.cocktailDbApiService = cocktailDbApiService;
        this.cocktailService = cocktailService;
        this.cocktailRepository = cocktailRepository;
    }

    @GetMapping("/test")
    private String cocktailListAlcohol(Model model) {
        List<CocktailDTO> cocktailDTOList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            cocktailDTOList.add(cocktailDbApiService.getCocktailDto());
        }
        List<CocktailDTO> distinctList = cocktailDTOList.stream().filter(x -> !(x == null)).distinct().collect(Collectors.toList());
        List<Cocktail> cocktailList = cocktailService.getCocktail(distinctList);
        cocktailRepository.saveAll(cocktailList);

        return "redirect:/cocktailVibe/";
    }
}
