package pl.put.poznan.transformer.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GUIController {
    @GetMapping("/")
    public String home() {
        return "redirect:/index.html";
    }
}
