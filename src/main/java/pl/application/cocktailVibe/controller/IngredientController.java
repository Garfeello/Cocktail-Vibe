package pl.application.cocktailVibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.application.cocktailVibe.model.Ingredient;
import pl.application.cocktailVibe.repository.IngredientRepository;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cocktailVibe/ingredient")
public class IngredientController {

    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/addIngredient")
    private String initAddIngredient(Model model, Locale locale) {
        Ingredient ingredient = new Ingredient();
        ingredient.setLanguage(locale.getLanguage());
        model.addAttribute("ingredient", ingredient);
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
    private String ingredientList(Model model, Locale locale) {
        List<Ingredient> ingredientList = new ArrayList<>();
        Optional<List<Ingredient>> optionalIngredients = ingredientRepository.findAllByLanguage(locale.getLanguage());
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
