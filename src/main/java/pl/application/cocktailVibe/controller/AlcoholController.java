package pl.application.cocktailVibe.controller;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Picture;
import pl.application.cocktailVibe.repository.AlcoholRepository;
import pl.application.cocktailVibe.repository.PictureRepository;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/cocktailVibe")
public class AlcoholController {

    private final AlcoholRepository alcoholRepository;

    public AlcoholController(AlcoholRepository alcoholRepository) {
        this.alcoholRepository = alcoholRepository;
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


}
