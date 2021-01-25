package pl.application.cocktailVibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Picture;
import pl.application.cocktailVibe.repository.AlcoholRepository;
import pl.application.cocktailVibe.services.GoogleTranslateService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/cocktailVibe")
public class AlcoholController {

    private final AlcoholRepository alcoholRepository;
    private final GoogleTranslateService googleTranslateService;

    public AlcoholController(AlcoholRepository alcoholRepository, GoogleTranslateService googleTranslateService) {
        this.alcoholRepository = alcoholRepository;
        this.googleTranslateService = googleTranslateService;
    }

    @ModelAttribute("alcoholTypeList")
    private List<String> alcoholTypeList() {
        return Arrays.asList("Vodka", "Whiskey", "Brandy", "Vermouth", "Cognac", "Rum", "Gin", "Port Wine", "Cognac", "Spirit");
    }

    @GetMapping("/addAlcohol")
    private String initAddAlcohol(Model model) {
        model.addAttribute("alcohol", new Alcohol());
        return "alcohol/alcoholForm";
    }

    @PostMapping("/addAlcohol")
    private String addAlcohol(@ModelAttribute @Valid Alcohol alcohol, BindingResult result,
                              @RequestParam("image") MultipartFile file) {

        if (result.hasErrors()) {
            return "alcohol/alcoholForm";
        }

        Picture picture = new Picture();
        picture.setName(alcohol.getName());
        try {
            picture.setImage(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        alcohol.setPicture(picture);
        alcoholRepository.save(alcohol);
        return "redirect:/cocktailVibe/";
    }

    @GetMapping("/editAlcohol")
    private String initEditAlcohol() {
        return "";
    }

    @PostMapping("/editAlcohol")
    private String editAlcohol() {
        return "";
    }

    @PostMapping("/deleteAlcohol")
    private String deleteAlcohol() {
        return "";
    }

    @GetMapping("/alcoholList")
    private String getAlcoholListEng(Model model) {
        model.addAttribute("alcoholList", alcoholRepository.findAlcoholByLanguage("Eng"));
        return "alcohol/alcoholList";
    }

    @GetMapping("/alcoholListPl")
    private String getAlcoholListPl(Model model) {
        model.addAttribute("alcoholList", alcoholRepository.findAlcoholByLanguage("Pl"));
        return "alcohol/alcoholList";
    }

    @GetMapping("/translateAlcohol")
    private String translateAlcohol(@RequestParam String alcoholName, Model model) {
        model.addAttribute("alcohol", googleTranslateService.translateAndGetAlcohol(alcoholName));
        return "alcohol/translatedAlcoholInfo";
    }


}
