package es.webapp3.movieframe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.webapp3.movieframe.service.MovieService;

@RestController
public class Home {
    
    @Autowired 
    private MovieService movieService;
    
    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("movies", movieService.findAll());
        return "initial_screen.html";
    }

    @GetMapping("/sign_up")
    public String sign_up (){

        return "signup_screen.html";
    }

    @GetMapping("/log_in")
    public String log_in(){

        return "login_screen.html";
    }

}
