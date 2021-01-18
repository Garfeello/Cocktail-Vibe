package pl.application.cocktailVibe.controller;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import pl.application.cocktailVibe.model.Picture;
import pl.application.cocktailVibe.repository.CocktailRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.Base64;

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
