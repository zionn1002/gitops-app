package ops.app1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Value("${app.profile:default}")
    private String profile;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("appName", "ops-app1");
        model.addAttribute("profile", profile);
        model.addAttribute("message", "Hello from ops-app1 (" + profile + ")");
        return "home";
    }
}