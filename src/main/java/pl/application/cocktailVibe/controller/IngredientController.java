package pl.application.cocktailVibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.repository.IngredientRepository;
import pl.application.cocktailVibe.services.GoogleTranslateService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cocktailVibe/ingredient")
public class IngredientController {

    private final IngredientRepository ingredientRepository;
    private final GoogleTranslateService googleTranslateService;

    public IngredientController(IngredientRepository ingredientRepository, GoogleTranslateService googleTranslateService) {
        this.ingredientRepository = ingredientRepository;
        this.googleTranslateService = googleTranslateService;
    }

    @GetMapping("/addIngredient")
    private String initAddIngredient(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredient/ingredientForm";
    }

    @PostMapping("/addIngredient")
    private String addIngredient(@ModelAttribute @Valid Ingredient ingredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredient/ingredientForm";
        }
        ingredientRepository.save(ingredient);
        return "redirect:/cocktailVibe/ingredient/ingredientList";
    }

    @GetMapping("/ingredientList")
    private String ingredientList(Model model) {
        List<Ingredient> ingredientList = new ArrayList<>();
        Optional<List<Ingredient>> optionalIngredients = ingredientRepository.findAllByLanguage("en");
        if (optionalIngredients.isPresent()) {
            ingredientList = sortFromOptional(optionalIngredients.get());
        }
        model.addAttribute("ingredientList", ingredientList);
        return "ingredient/ingredientList";
    }

    @GetMapping("/ingredientListPl")
    private String ingredientListPl(Model model) {
        List<Ingredient> ingredientList = new ArrayList<>();
        Optional<List<Ingredient>> optionalIngredients = ingredientRepository.findAllByLanguage("pl");
        if (optionalIngredients.isPresent()) {
            ingredientList = sortFromOptional(optionalIngredients.get());
        }
        model.addAttribute("ingredientList", ingredientList);
        return "ingredient/ingredientList";
    }

    private List<Ingredient> sortFromOptional(List<Ingredient> ingredientList) {
        return ingredientList.stream().sorted(Comparator.comparing(Ingredient::getName)).collect(Collectors.toList());
    }

}
