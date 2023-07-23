package co.id.bankbsi.middlewaresnippet.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    @GetMapping("/adminPage")
    public String showAdminPage() {
        return "admin-page";
    }

    @GetMapping("/userPage")
    public String showUserPage() {
        return "user-page";
    }

    @GetMapping("/accessDenied")
    public String showAccessDeniedPage() {
        return "error/access-denied-page";
    }


}
