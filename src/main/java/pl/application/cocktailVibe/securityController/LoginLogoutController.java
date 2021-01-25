package pl.application.cocktailVibe.securityController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginLogoutController {

    @GetMapping("/login")
    private String login() {
        return "security/login";
    }

    @GetMapping("/logoutSuccess")
    private String logout() {
        return "security/logout";
    }

}
