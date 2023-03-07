package es.webapp3.movieframe.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class director_controller {

    @GetMapping("/director")
    public String director(Model model){
        //model.addAttribute("name", "Hola mundo");
        return "director_screen";
    }

   
}
