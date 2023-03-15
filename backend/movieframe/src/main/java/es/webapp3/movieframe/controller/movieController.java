package es.webapp3.movieframe.controller;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.webapp3.movieframe.model.Director;
import es.webapp3.movieframe.model.Movie;
import es.webapp3.movieframe.model.Review;
import es.webapp3.movieframe.service.MovieService;
import es.webapp3.movieframe.service.ReviewService;
import es.webapp3.movieframe.service.DirectorService;

@RestController
public class movieController {
    
    @Autowired
    private MovieService movieService;

    @Autowired
    private DirectorService directorService;
	
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovies(Model model){

        return ResponseEntity.ok(movieService.findAll());
    }

    
    @GetMapping("/directors/{id}")
    public ResponseEntity<Director> getDirector(@PathVariable long id){
        Optional<Director> director = directorService.findById(id);
        return ResponseEntity.ok(director.get());
    }
	
	
    @GetMapping("/movies/{id}")
    public ResponseEntity<Object> getMovie(Model model,@PathVariable Long id) {

        Optional<Movie> movie = movieService.findById(id);

        if(movie.isPresent()){
            return ResponseEntity.ok(movie.get());
        }else{
            return ResponseEntity.notFound().build();
        }   
    }
     
    @PostMapping("/movies/{id}/review/new")
    public ResponseEntity<Review> newReview(Model model,@PathVariable Long id,@RequestBody Review review){

        Optional<Movie> movie = movieService.findById(id);

        movie.get().setReview(review);

        movieService.save(movie.get());

        URI location = fromCurrentRequest().path("/movies/{id}/review/new")
        .buildAndExpand(review.getId()).toUri();

        return ResponseEntity.created(location).body(review); 
    }

    /* 

    @GetMapping("/movie/{title}")
	public String showMovie(Model model, @PathVariable String title) {

		Movie movie = movieService.findSingleByTitle(title);
		model.addAttribute("movie",movie);
		return "movie_screen";

	}

    @GetMapping("/movie/{name}")
    public String findMovies(Model model,@RequestParam String name){
        List<Movie> movies = movieService.findByTitle(name);

        model.addAttribute("movieframe",movies);
        return "initial_screen";
    }
*/
	
    
    @GetMapping("/movie/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
        
        Optional<Movie> movie = movieService.findById(id);
        
        if (movie.isPresent() && movie.get().getImageFile() != null) {
            Resource file = new InputStreamResource(movie.get().getImageFile().getBinaryStream());
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .contentLength(movie.get().getImageFile().length())
                .body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @GetMapping("/movie/{id}/director/image")
    public ResponseEntity<Object> downloadDirectorImage(@PathVariable long id) throws SQLException {
        
        Optional<Director> director = directorService.findById(id);
        
        if (director.isPresent() && director.get().getImageFile() != null) {
            Resource file = new InputStreamResource(director.get().getImageFile().getBinaryStream());
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .contentLength(director.get().getImageFile().length())
                .body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }   
    
}
