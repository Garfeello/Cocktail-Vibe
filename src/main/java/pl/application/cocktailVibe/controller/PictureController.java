package pl.application.cocktailVibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.application.cocktailVibe.repository.CocktailRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.OutputStream;


@Controller
@RequestMapping("pictureController")
public class PictureController {

    private final CocktailRepository cocktailRepository;

    public PictureController(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    @GetMapping("/getPicture/{id}")
    private void getPicture(@PathVariable Long id, HttpServletResponse response) {
        try  {
            OutputStream o = response.getOutputStream();
            o.write(cocktailRepository.getOne(id).getPicture().getImage());
            response.setContentType("image/jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
