package pl.application.cocktailVibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.application.cocktailVibe.model.Alcohol;
import pl.application.cocktailVibe.model.Picture;
import pl.application.cocktailVibe.model.User;
import pl.application.cocktailVibe.repository.AlcoholRepository;
import pl.application.cocktailVibe.repository.UserRepository;
import pl.application.cocktailVibe.services.GoogleTranslateService;
import pl.application.cocktailVibe.services.PictureService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/cocktailVibe")
public class AlcoholController {

    private final AlcoholRepository alcoholRepository;
    private final GoogleTranslateService googleTranslateService;
    private final UserRepository userRepository;
    private final PictureService pictureService;

    public AlcoholController(AlcoholRepository alcoholRepository, GoogleTranslateService googleTranslateService,
                             UserRepository userRepository, PictureService pictureService) {
        this.alcoholRepository = alcoholRepository;
        this.googleTranslateService = googleTranslateService;
        this.userRepository = userRepository;
        this.pictureService = pictureService;
    }

    @ModelAttribute("alcoholTypeList")
    private List<String> alcoholTypeList() {
        return Arrays.asList("Vodka", "Whiskey", "Brandy", "Vermouth", "Cognac", "Rum", "Gin", "Port Wine", "Cognac", "Spirit");
    }

    @GetMapping("/addAlcohol")
    private String initAddAlcohol(Model model, Locale locale) {
        Alcohol alcohol = new Alcohol();
        alcohol.setLanguage(locale.getLanguage());
        model.addAttribute("alcohol", alcohol);
        return "alcohol/alcoholForm";
    }

    @PostMapping("/addAlcohol")
    private String addAlcohol(@ModelAttribute @Valid Alcohol alcohol, BindingResult result,
                              @RequestParam("image") MultipartFile file, Principal principal) {
        if (result.hasErrors()) {
            return "alcohol/alcoholForm";
        }
        Picture picture = pictureService.getPicture(alcohol.getName(), file);
        Optional<User> optionalUser = userRepository.findByEmail(principal.getName());
        optionalUser.ifPresent(alcohol::setUser);
        alcohol.setPicture(picture);
        alcoholRepository.save(alcohol);
        return "redirect:/cocktailVibe/user/alcohols";
    }

    @GetMapping("/editAlcohol")
    private String initEditAlcohol(@RequestParam Long alcoholId, Model model) {
        Optional<Alcohol> optionalAlcohol = alcoholRepository.findById(alcoholId);
        optionalAlcohol.ifPresent(alcohol -> model.addAttribute("alcohol", alcohol));
        return "alcohol/alcoholForm";
    }

    @PostMapping("/editAlcohol")
    private String editAlcohol(@ModelAttribute @Valid Alcohol alcohol, BindingResult bindingResult,
                               @RequestParam("image") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "alcohol/alcoholForm";
        }
        if (!file.isEmpty()) {
            Picture picture = pictureService.getPicture(alcohol.getName(), file);
            alcohol.setPicture(picture);
        }
        alcoholRepository.save(alcohol);
        return "redirect:/cocktailVibe/user/alcohols";
    }

    @PostMapping("/deleteAlcohol")
    private String deleteAlcohol(@RequestParam Long alcoholId) {
        alcoholRepository.deleteById(alcoholId);
        return "redirect:/cocktailVibe/user/alcohols";
    }

    @GetMapping("/alcoholList")
    private String getAlcoholListEng(Model model, Locale locale) {
        model.addAttribute("alcoholList", alcoholRepository.findAlcoholByLanguage(locale.getLanguage()).orElse(Collections.emptyList()));
        return "alcohol/alcoholList";
    }

    @GetMapping("/translateAlcohol")
    private String translateAlcohol(@RequestParam(required = false) String alcoholName, Model model, Locale locale) {
        if (alcoholName == null) {
            return "alcohol/translatedAlcoholInfo";
        }

        String translateTo = locale.getLanguage().equals("pl") ? "en" : "pl";
        alcoholRepository.save(googleTranslateService.translateAndGetAlcohol(alcoholName, translateTo));
        model.addAttribute("alcohol", alcoholRepository.findFirstByNameAndLanguage(alcoholName, translateTo).orElse(new Alcohol()));
        return "alcohol/translatedAlcoholInfo";
    }


}
