package pl.application.cocktailVibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.application.cocktailVibe.repository.AlcoholRepository;
import pl.application.cocktailVibe.repository.CocktailRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.OutputStream;


@Controller
@RequestMapping("/pictureController")
public class PictureController {

    private final CocktailRepository cocktailRepository;
    private final AlcoholRepository alcoholRepository;

    public PictureController(CocktailRepository cocktailRepository, AlcoholRepository alcoholRepository) {
        this.cocktailRepository = cocktailRepository;
        this.alcoholRepository = alcoholRepository;
    }

    @GetMapping("/getPicture/{id}")
    private void getCocktailPicture(@PathVariable Long id, HttpServletResponse response) {
        try  {
            OutputStream o = response.getOutputStream();
            o.write(cocktailRepository.getOne(id).getPicture().getImage());
            response.setContentType("image/jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getAlcoholPicture/{id}")
    private void getAlcoholPicture(@PathVariable Long id, HttpServletResponse response) {
        try  {
            OutputStream o = response.getOutputStream();
            o.write(alcoholRepository.getOne(id).getPicture().getImage());
            response.setContentType("image/jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
