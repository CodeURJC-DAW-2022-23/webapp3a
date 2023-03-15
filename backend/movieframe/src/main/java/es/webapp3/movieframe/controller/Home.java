package es.webapp3.movieframe.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonView;

import es.webapp3.movieframe.model.Director;
import es.webapp3.movieframe.model.Movie;
import es.webapp3.movieframe.model.Review;
import es.webapp3.movieframe.service.MovieService;
import es.webapp3.movieframe.service.DirectorService;

@Controller
public class home {

    @Autowired
    private MovieService movieService;

    @Autowired
    private DirectorService directorService;
    
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("movieframe", movieService.findAll());
        return "initial_screen";
    }

    @PostMapping("/movie/{id}/review/new")
    public String newReview(Model model,@PathVariable Long id,@RequestParam int rating, @RequestParam String coments){

        Optional<Movie> movie = movieService.findById(id);

        if(movie.isPresent()){
            
            movie.get().setReview(new Review(rating,coments));
            movieService.save(movie.get());

            model.addAttribute("title",movie.get().getTitle());
            model.addAttribute("gender",movie.get().getCategory());
            model.addAttribute("description",movie.get().getDescription());
            model.addAttribute("picture",movie.get().getImageFile());
            model.addAttribute("directors",movie.get().getDirectors());

            return "movie_screen";
        }else{
            return "404";
        }   
    }

 
    @GetMapping("/movie/{id}")
    public String getMovie(Model model,@PathVariable Long id){

        Optional<Movie> movie = movieService.findById(id);

        if(movie.isPresent()){
            model.addAttribute("title",movie.get().getTitle());
            model.addAttribute("gender",movie.get().getCategory());
            model.addAttribute("description",movie.get().getDescription());
            model.addAttribute("picture",movie.get().getImageFile());
            model.addAttribute("directors",movie.get().getDirectors());
            return "movie_screen";
        }else{
            return "404";
        }   
    }

    @GetMapping("/movie/{id}/director")
    public String getDirector(Model model,@PathVariable Long id){

        Optional<Director> director = directorService.findById(id);
        
        if(director.isPresent()){
            model.addAttribute("avatar",director.get().getImageFile());
            model.addAttribute("director",director.get().getDirector());
            model.addAttribute("biography",director.get().getBiography());
            model.addAttribute("name",director.get().getName());
            model.addAttribute("born",director.get().getBorn());
            model.addAttribute("genre",director.get().getGenre());
            model.addAttribute("residence",director.get().getResidence());
            model.addAttribute("score",director.get().getScore());
            return "director_screen";
        }else{
            return "404";
        }   
    }

    @GetMapping("/director")
    public String showDirector(){
        return "director_screen";
    }
    
    @GetMapping("/log_in")
    public String login(){
        return "login_screen";
    }

    @GetMapping("/log_error")
    public String loginerror(){
        return "404";
    }

    @GetMapping("/show_reviews")
    public String showReviews(){
        return "modification_reviews_screen";
    }

    @GetMapping("/sign_up")
    public String signup(){
        return "signup_screen";
    }
    
}
