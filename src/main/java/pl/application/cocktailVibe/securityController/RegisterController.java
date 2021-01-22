package pl.application.cocktailVibe.securityController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.application.cocktailVibe.dto.UserDTO;
import pl.application.cocktailVibe.services.UserService;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    private String initRegister(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "security/registerForm";
    }

    @PostMapping("/register")
    private String register(@ModelAttribute @Valid UserDTO userDTO, BindingResult result){
        if (result.hasErrors()){
            return "security/registerForm";
        }
        userService.registerNewUserAccount(userDTO);
        return "redirect:/cocktailVibe/";
    }

}
