package es.webapp3.movieframe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.webapp3.movieframe.model.movie;
import es.webapp3.movieframe.service.MovieService;

@Controller
public class MovieController {

    @Autowired 
    private MovieService movieService;
    
    @GetMapping("/movie/{title}")
	public String showBook(Model model, @PathVariable String title) {

		movie movie = movieService.findSingleByTitle(title);
		model.addAttribute("movie",movie);
		return "movie_screen";

	}


    
}
