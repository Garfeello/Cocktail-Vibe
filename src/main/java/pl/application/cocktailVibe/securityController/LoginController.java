package pl.application.cocktailVibe.securityController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    private String initLogin(){
        return "security/login";
    }
}
