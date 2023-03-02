package es.webapp3.movieframe.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.webapp3.movieframe.model.Movie;

@Controller
public class movieController {
    
    private List<Movie> movieframe = new ArrayList<>();

    /*@GetMapping("/")
    public String databaseInitializer(Model model){

        movieframe.add(new Movie("Tony Stark creates the Ultron Program to...","adventure","reavender","7.4"));
        
        model.addAttribute("movieframe",movieframe);

        return "initial_screen.html";
    }*/

    @GetMapping("/particular")
    public String movieInfo(Model model){
//obtener de la bd la peli seleccionada, añadirlo al modelo y devolver la pantalla

        return "movie_screen.html";
    }
}
