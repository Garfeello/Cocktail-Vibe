package pl.application.cocktailVibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.repository.IngredientRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cocktailVibe/ingredient")
public class IngredientController {

    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/addIngredient")
    private String initAddIngredient(Model model){
        model.addAttribute("ingredient", new Ingredient());
        return "ingredient/ingredientForm";
    }

    @PostMapping("/addIngredient")
    private String addIngredient(@ModelAttribute Ingredient ingredient, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "ingredient/ingredientForm";
        }
        ingredientRepository.save(ingredient);
        return "redirect:/cocktailVibe/ingredient/ingredientList";
    }

    @GetMapping("/ingredientList")
    private String ingredientList(Model model){
        List<Ingredient> sortedIngredients = ingredientRepository.findAllIngredients()
                .stream()
                .sorted(Comparator.comparing(Ingredient::getName))
                .collect(Collectors.toList());
        model.addAttribute("ingredientList", sortedIngredients);
        return "ingredient/ingredientList";
    }









}
