package es.webapp3.movieframe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class pruebaController {
    
    @GetMapping("/")
    public String index(){

        return "initial_screen";
    }

    @GetMapping("/sign_up")
    public String sign_up (){

        return "signup_screen";
    }

    @GetMapping("/log_in")
    public String log_in(){

        return "login_screen";
    }
}
