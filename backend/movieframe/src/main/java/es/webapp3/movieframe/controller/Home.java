package es.webapp3.movieframe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.service.MovieService;
import es.webapp3.movieframe.service.DirectorService;
import es.webapp3.movieframe.service.ReviewService;
import es.webapp3.movieframe.service.UserService;

@Controller
public class home {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private DirectorService directorService;
    
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("movieframe", movieService.findAll());
        return "initial_screen";
    }

    @PostMapping("/movies/name")
    public String newReview(Model model,@RequestParam String name){

        List<Movie> movies = movieService.findByTitle(name);

        model.addAttribute("movieframe",movies);

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
 
    @GetMapping("/reviews/user/{id}")
    public String getReviewsUser(Model model,@PathVariable Long id,Pageable pageable){

        Optional<User> user = userService.findById(id);

        model.addAttribute("reviews",user.get().getReviews());
        
        return "reviews_screen";
    }

    @GetMapping("/reviews")
    public String getReviews(Model model,Pageable pageable){

        Page<Review> reviews = reviewService.findAll(pageable);

        model.addAttribute("reviews",reviews);
        
        return "modification_reviews_screen";
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
