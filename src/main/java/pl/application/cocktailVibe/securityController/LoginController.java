package pl.application.cocktailVibe.securityController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    private String initLogin(){
        return "security/login";
    }

    @PostMapping("/login")
    private String performLogin(){



        return "redirect:/cocktailVibe/";
    }
}
