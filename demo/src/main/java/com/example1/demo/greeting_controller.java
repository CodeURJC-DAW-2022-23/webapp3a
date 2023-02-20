package com.example1.demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class greeting_controller {

    @GetMapping("/greeting")
    public String greeting(Model model){
        model.addAttribute("name", "Hola mundo");
        return "greeting_template";
    }
}
