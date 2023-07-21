package co.id.bankbsi.middlewaresnippet.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class Home {

    @GetMapping("/dashboard")
    public ModelMap index() {
        return new ModelMap();
    }

    @GetMapping("/login")
    public ModelMap login() {
        return new ModelMap();
    }

}
