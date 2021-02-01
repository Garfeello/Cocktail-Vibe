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

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cocktailVibe")
public class CocktailController {

    private final AlcoholRepository alcoholRepository;
    private final IngredientRepository ingredientRepository;
    private final CocktailRepository cocktailRepository;
    private final GoogleTranslateService googleTranslateService;
    private final UserRepository userRepository;


    public CocktailController(AlcoholRepository alcoholRepository, IngredientRepository ingredientRepository,
                              CocktailRepository cocktailRepository, GoogleTranslateService googleTranslateService,
                              UserRepository userRepository) {
        this.alcoholRepository = alcoholRepository;
        this.ingredientRepository = ingredientRepository;
        this.cocktailRepository = cocktailRepository;
        this.googleTranslateService = googleTranslateService;
        this.userRepository = userRepository;
    }

    @ModelAttribute("alcoholList")
    private List<Alcohol> alcoholList() {
        return alcoholRepository.findAllAlcohols();
    }

    @ModelAttribute("ingredientList")
    private List<Ingredient> ingredientList() {
        return ingredientRepository.findAllIngredients()
                .stream()
                .sorted(Comparator.comparing(Ingredient::getName))
                .collect(Collectors.toList());
    }

    @GetMapping("/addCocktail")
    private String InitAddCocktail(Model model) {
        model.addAttribute("cocktail", new Cocktail());
        return "cocktail/cocktailForm";
    }

    @PostMapping("/addCocktail")
    private String addCocktail(@ModelAttribute @Valid Cocktail cocktail, BindingResult result,
                               @RequestParam("image") MultipartFile file, Principal principal) {
        if (result.hasErrors()) {
            return "cocktail/cocktailForm";
        }
        Picture picture = getPicture(cocktail.getName(), file);
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
            Picture picture = getPicture(cocktail.getName(), file);
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
            Picture picture = getPicture(cocktail.getName(), file);
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

    @GetMapping("/cocktailList")
    private String getCocktailListEng(Model model) {
        model.addAttribute("cocktailList", cocktailRepository.findCocktailsByLanguage("en"));
        return "cocktail/cocktailList";
    }

    @GetMapping("/cocktailListPl")
    private String getCocktailListPl(Model model) {
        model.addAttribute("cocktailList", cocktailRepository.findCocktailsByLanguage("pl"));
        return "cocktail/cocktailListPl";
    }

    @GetMapping("/translateCocktailToPl")
    private String translateCocktailToPl(@RequestParam String cocktailName, @RequestParam String translateFrom,
                                         @RequestParam String translateTo, Model model) {
        cocktailRepository.save(googleTranslateService.translateAngGetCocktail(cocktailName, translateFrom, translateTo));
        model.addAttribute("cocktail", List.of(cocktailRepository.findFirstByNameAndLanguage(cocktailName, translateTo).orElse(new Cocktail())));
        model.addAttribute("searchedString", "Przetlumaczono !");
        return "cocktail/cocktailInfo";
    }

    @GetMapping("/translateCocktailToEn")
    private String translateCocktailToEn(@RequestParam String cocktailName, @RequestParam String translateFrom,
                                         @RequestParam String translateTo, Model model) {
        cocktailRepository.save(googleTranslateService.translateAngGetCocktail(cocktailName, translateFrom, translateTo));
        model.addAttribute("cocktail", List.of(cocktailRepository.findFirstByNameAndLanguage(cocktailName, translateTo).orElse(new Cocktail())));
        model.addAttribute("searchedString", "Translated !");
        return "cocktail/cocktailInfo";
    }

    @GetMapping("/getCocktailsFromIngredient")
    private String getCocktailsFromIngredient(@RequestParam String ingredientName, Model model) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findFirstByName(ingredientName);
        Optional<List<Cocktail>> cocktailOptional = Optional.empty();
        if (ingredientOptional.isPresent()) {
            cocktailOptional = cocktailRepository.findCocktailsByIngredients(ingredientOptional.get());
        }
        cocktailOptional.ifPresent(cocktails -> model.addAttribute("cocktail", cocktails));
        model.addAttribute("searchedString", "Searching by " + ingredientName);
        return "cocktail/cocktailInfo";
    }

    // czy to moze byc w kontrolerze?
    private Picture getPicture(String cocktailName, MultipartFile file) {
        Picture picture = new Picture();
        picture.setName(cocktailName + ".jpg");
        try {
            picture.setImage(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picture;
    }
}
