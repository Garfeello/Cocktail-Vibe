package pl.application.cocktailVibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Cocktail;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.model.Picture;
import pl.application.cocktailVibe.repository.AlcoholRepository;
import pl.application.cocktailVibe.repository.CocktailRepository;
import pl.application.cocktailVibe.repository.IngredientRepository;
import pl.application.cocktailVibe.services.CocktailDbService;
import pl.application.cocktailVibe.services.GoogleTranslateService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cocktailVibe")
public class CocktailController {

    private final AlcoholRepository alcoholRepository;
    private final IngredientRepository ingredientRepository;
    private final CocktailRepository cocktailRepository;
    private final GoogleTranslateService googleTranslateService;


    public CocktailController(AlcoholRepository alcoholRepository, IngredientRepository ingredientRepository,
                              CocktailRepository cocktailRepository, GoogleTranslateService googleTranslateService) {
        this.alcoholRepository = alcoholRepository;
        this.ingredientRepository = ingredientRepository;
        this.cocktailRepository = cocktailRepository;
        this.googleTranslateService = googleTranslateService;
    }

    @ModelAttribute("alcoholList")
    private List<Alcohol> alcoholList() {
        return alcoholRepository.findAllAlcohols();
    }

    @ModelAttribute("ingredientList")
    private List<Ingredient> ingredientList() {
        return ingredientRepository.findAllIngredients();
    }

    @GetMapping("/addCocktail")
    private String addCocktailInit(Model model) {
        model.addAttribute("cocktail", new Cocktail());
        return "cocktail/cocktailForm";
    }

    @PostMapping("/addCocktail")
    private String addCocktail(@ModelAttribute @Valid Cocktail cocktail, BindingResult result,
                               @RequestParam("image") MultipartFile file) {
        if (result.hasErrors()) {
            return "cocktail/cocktailForm";
        }
        Picture picture = new Picture();
        picture.setName(cocktail.getName());
        try {
            picture.setImage(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        cocktail.setPicture(picture);
        cocktailRepository.save(cocktail);
        return "redirect:/cocktailVibe/";
    }

    @GetMapping("/editCocktail")
    private String editCocktailInit() {
        return "";
    }

    @PostMapping("/editCocktail")
    private String editCocktail() {
        return "";
    }

    @GetMapping("/deleteCocktail")
    private String deleteCocktail() {
        return "";
    }

    @GetMapping("/cocktailList")
    private String getCocktailListEng(Model model) {
        model.addAttribute("cocktailList", cocktailRepository.findCocktailsByLanguage("Eng"));
        return "cocktail/cocktailList";
    }

    @GetMapping("/cocktailListPl")
    private String getCocktailListPl(Model model) {
        model.addAttribute("cocktailList", cocktailRepository.findCocktailsByLanguage("Pl"));
        return "cocktail/cocktailList";
    }


    @GetMapping("/translateCocktail")
    private String translateCocktail(@RequestParam String cocktailName, Model model) {
        model.addAttribute("cocktail", List.of(googleTranslateService.translateAngGetCocktail(cocktailName)));
        return "mainPage/cocktailInfo";
    }


}
