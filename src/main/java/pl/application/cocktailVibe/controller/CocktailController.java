package pl.application.cocktailVibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cocktailVibe")
public class CocktailController {

    @GetMapping("/addCocktail")
    private String addCocktailInit() {


        return "drink/cocktailForm";
    }


    @PostMapping("/addCocktail")
    private String addCocktail() {


        return "redirect:/cocktailVibe/";
    }


}
