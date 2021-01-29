package pl.application.cocktailVibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.application.cocktailVibe.dto.CocktailDTO;
import pl.application.cocktailVibe.services.CocktailDbApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cocktailVibe/adminPanel")
public class AdminPanelController {

    private final CocktailDbApiService cocktailDbApiService;

    public AdminPanelController(CocktailDbApiService cocktailDbApiService) {
        this.cocktailDbApiService = cocktailDbApiService;
    }

    @GetMapping("/test")
    private String cocktailListAlcohol(Model model) {
        List<CocktailDTO> cocktailDTOList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            cocktailDTOList.add(cocktailDbApiService.getCocktailDto());
        }
        List<CocktailDTO> distinctList = cocktailDTOList.stream()
                .filter(x -> !(x == null))
                .distinct()
                .collect(Collectors.toList());
        model.addAttribute("cocktailListSize", distinctList.size());
        model.addAttribute("cocktailList", distinctList);
        return "adminPanel/cocktailListAdmin";
    }

}
