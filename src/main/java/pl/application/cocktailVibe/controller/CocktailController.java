package pl.application.cocktailVibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.application.cocktailVibe.model.*;
import pl.application.cocktailVibe.repository.AlcoholRepository;
import pl.application.cocktailVibe.repository.CocktailRepository;
import pl.application.cocktailVibe.repository.IngredientRepository;
import pl.application.cocktailVibe.repository.UserRepository;
import pl.application.cocktailVibe.services.GoogleTranslateService;
import pl.application.cocktailVibe.services.PictureService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/cocktailVibe")
public class CocktailController {

    private final AlcoholRepository alcoholRepository;
    private final IngredientRepository ingredientRepository;
    private final CocktailRepository cocktailRepository;
    private final GoogleTranslateService googleTranslateService;
    private final UserRepository userRepository;
    private final PictureService pictureService;

    public CocktailController(AlcoholRepository alcoholRepository, IngredientRepository ingredientRepository,
                              CocktailRepository cocktailRepository, GoogleTranslateService googleTranslateService,
                              UserRepository userRepository, PictureService pictureService) {
        this.alcoholRepository = alcoholRepository;
        this.ingredientRepository = ingredientRepository;
        this.cocktailRepository = cocktailRepository;
        this.googleTranslateService = googleTranslateService;
        this.userRepository = userRepository;
        this.pictureService = pictureService;
    }

    @ModelAttribute("alcoholList")
    private List<Alcohol> alcoholList(Locale locale) {
        return alcoholRepository.findAlcoholByLanguage(locale.getLanguage()).orElse(Collections.emptyList());
    }

    @ModelAttribute("ingredientList")
    private List<Ingredient> ingredientList(Locale locale) {
        return ingredientRepository.findAllByLanguage(locale.getLanguage()).orElse(Collections.emptyList());
    }

    @GetMapping("/cocktailList")
    private String getCocktailListEng(Model model, Locale locale) {
        model.addAttribute("cocktailList", cocktailRepository.findCocktailsByLanguage(locale.getLanguage()));
        return "cocktail/cocktailList";
    }

    @GetMapping("/addCocktail")
    private String InitAddCocktail(Model model, Locale locale) {
        Cocktail cocktail = new Cocktail();
        cocktail.setLanguage(locale.getLanguage());
        model.addAttribute("cocktail", cocktail);
        return "cocktail/cocktailForm";
    }

    @PostMapping("/addCocktail")
    private String addCocktail(@ModelAttribute @Valid Cocktail cocktail, BindingResult result,
                               @RequestParam("image") MultipartFile file, Principal principal) {
        if (result.hasErrors()) {
            return "cocktail/cocktailForm";
        }
        Picture picture = pictureService.getPicture(cocktail.getName(), file);
        Optional<User> optionalUser = userRepository.findByEmail(principal.getName());
        optionalUser.ifPresent(cocktail::setUser);
        cocktail.setPicture(picture);
        cocktailRepository.save(cocktail);
        return "redirect:/cocktailVibe/user/cocktails";
    }

    @GetMapping("/editCocktail")
    private String initEditCocktail(@RequestParam Long cocktailId, Model model) {
        Optional<Cocktail> optionalCocktail = cocktailRepository.findById(cocktailId);
        optionalCocktail.ifPresent(cocktail -> model.addAttribute("cocktail", cocktail));
        return "cocktail/cocktailForm";
    }

    @PostMapping("/editCocktail")
    private String editCocktail(@ModelAttribute @Valid Cocktail cocktail, BindingResult bindingResult,
                                @RequestParam("image") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "cocktail/cocktailForm";
        }
        if (!file.isEmpty()) {
            Picture picture = pictureService.getPicture(cocktail.getName(), file);
            cocktail.setPicture(picture);
        }
        cocktailRepository.save(cocktail);
        return "redirect:/cocktailVibe/user/cocktails";
    }

    @GetMapping("/getNewCocktailFromCopy")
    private String initGetCocktailFromCopy(@RequestParam Long cocktailId, Model model) {
        model.addAttribute("cocktail", cocktailRepository.findById(cocktailId).orElse(new Cocktail()));
        return "cocktail/cocktailForm";
    }

    @PostMapping("/getNewCocktailFromCopy")
    private String getCocktailFromCopy(@ModelAttribute @Valid Cocktail cocktail, BindingResult result,
                                       @RequestParam("image") MultipartFile file, Principal principal) {
        if (result.hasErrors()) {
            return "cocktail/cocktailForm";
        }
        if (!file.isEmpty()) {
            Picture picture = pictureService.getPicture(cocktail.getName(), file);
            cocktail.setPicture(picture);
        }
        Optional<User> optionalUser = userRepository.findByEmail(principal.getName());
        optionalUser.ifPresent(cocktail::setUser);
        cocktailRepository.save(cocktail);
        return "redirect:/cocktailVibe/user/cocktails";
    }

    @PostMapping("/deleteCocktail")
    private String deleteCocktail(@RequestParam Long cocktailId) {
        cocktailRepository.deleteById(cocktailId);
        return "redirect:/cocktailVibe/user/cocktails";
    }

    @GetMapping("/translateCocktail")
    private String translateCocktail(@RequestParam String cocktailName, Model model, Locale locale) {
        String translateTo;
        if (locale.getLanguage().equals("pl")){
            translateTo = "en";
        } else {
            translateTo = "pl";
        }
        cocktailRepository.save(googleTranslateService.translateAngGetCocktail(cocktailName, locale.getLanguage(), translateTo));
        model.addAttribute("cocktail", List.of(cocktailRepository.findFirstByNameAndLanguage(cocktailName, translateTo).orElse(new Cocktail())));
        model.addAttribute("searchedString", "Translated !");
        return "cocktail/cocktailInfo";
    }

    @GetMapping("/getCocktailsFromIngredient")
    private String getCocktailsFromIngredient(@RequestParam(required = false) String ingredientName, Model model) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findFirstByName(ingredientName);
        Optional<List<Cocktail>> cocktailOptional = Optional.empty();
        if (ingredientOptional.isPresent()) {
            cocktailOptional = cocktailRepository.findCocktailsByIngredients(ingredientOptional.get());
        }
        cocktailOptional.ifPresent(cocktails -> model.addAttribute("cocktail", cocktails));
        model.addAttribute("searchedString", "Searching by " + ingredientName);
        return "cocktail/cocktailInfo";
    }
}
