package es.webapp3.movieframe.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import es.webapp3.movieframe.model.Director;
import es.webapp3.movieframe.model.Movie;
import es.webapp3.movieframe.model.Review;
import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.service.DirectorService;
import es.webapp3.movieframe.service.MovieService;
import es.webapp3.movieframe.service.ReviewService;
import es.webapp3.movieframe.service.UserService;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        
        if(principal != null) {
            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
            model.addAttribute("user", request.isUserInRole("USER"));
        } else {
            model.addAttribute("logged", false);
        }
    }
    
    @GetMapping("/movie")
    public String movieAdditionScreen(Model model,HttpServletRequest request){ 
    
        if(request.isUserInRole("ADMIN")){
            model.addAttribute("state","no movie added yet"); 

            return "movie_aggregation";
        }else{
            return "404";        
        }
    }

    @PostMapping("/movie/{id}")
    public String movieUpdating(Model model,Movie newMovie,@PathVariable Long id,HttpServletRequest request) throws IOException {

        if(request.isUserInRole("ADMIN")){

            Optional<Movie> movie = movieService.findById(id);

            if (movie.isPresent()) {

                movieService.update(id, newMovie);

                model.addAttribute("title",newMovie.getTitle());
                model.addAttribute("gender",newMovie.getCategory());
                model.addAttribute("description",newMovie.getDescription());
                model.addAttribute("picture",movie.get().getImageFile());
                model.addAttribute("directors",movie.get().getDirectors());

                return "movie_screen";
            } else {
                return "404";
            }
        } else {
            return "404";
        }
    }

    @PostMapping("/movie")
    public String newMovie(Model model,Movie movie,MultipartFile imageField,HttpServletRequest request)throws IOException {

        if(request.isUserInRole("ADMIN")){
            if(!imageField.isEmpty()){
                movie.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
                movie.setImage(true);
            }
            Movie movieSaved = movieService.save(movie);

            if(movieSaved != null){
                model.addAttribute("state","movie saved");
            } else {
                model.addAttribute("state","some mandatory fields like title, category or picture are incomplete");
            }

            return "movie_aggregation";    
        } else {
            return "404";
        }   
    }

    @PostMapping("/movie/{id}/review")
    public String newReview(Model model,@PathVariable Long id,@RequestParam int rating, @RequestParam String coments,HttpServletRequest request){
 
        if(request.isUserInRole("USER")){
            Optional<Movie> movie = movieService.findById(id);

            if(movie.isPresent()){

                Review review = new Review();
                review.setRating(rating);
                review.setComent(coments);

                review.setMovie(movie.get());
                
                //movie.get().setReview(review);

                Optional<User> user = userService.findByUsername(request.getUserPrincipal().getName());

                review.setUser(user.get());
                //user.get().setReview(review);

                reviewService.save(review);

                model.addAttribute("title",movie.get().getTitle());
                model.addAttribute("gender",movie.get().getCategory());
                model.addAttribute("description",movie.get().getDescription());
                model.addAttribute("picture",movie.get().getImageFile());
                model.addAttribute("directors",movie.get().getDirectors());

                return "movie_screen";
            }else{
                return "404";
            } 
        } else {
            return "404";
        }  
    }
 
    @GetMapping("/movie/{id}")
    public String getMovie(Model model,@PathVariable Long id,HttpServletRequest request){

            Optional<Movie> movie = movieService.findById(id);

            if(movie.isPresent()){
                model.addAttribute("title",movie.get().getTitle());
                model.addAttribute("gender",movie.get().getCategory());
                model.addAttribute("description",movie.get().getDescription());
                model.addAttribute("picture",movie.get().getImageFile());
                model.addAttribute("directors",movie.get().getDirectors());

                return "movie_screen";
            }else{
                return "error/404";
            } 
    }
    @GetMapping("/movie/{id}/director")
    public String getDirector(Model model,@PathVariable Long id,HttpServletRequest request){

        if(request.isUserInRole("USER") || request.isUserInRole("ADMIN")){
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
        } else {
            return "404";
        }  
    }

    @PostMapping("/movie/name")
    public String searchMovie(Model model, @RequestParam String name,Pageable page,HttpServletRequest request){

        Page<Movie> moviesFounded = movieService.findByTitle(name,PageRequest.of(0,10)); 
        model.addAttribute("movieframe",moviesFounded);
        return "initial_screen";    
    }
    
}
