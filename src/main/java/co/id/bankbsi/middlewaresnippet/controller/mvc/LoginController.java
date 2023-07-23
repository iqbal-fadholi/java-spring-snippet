package co.id.bankbsi.middlewaresnippet.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showMyloginPage(){
        return "pages/sign-in/login-page";
    }
}
