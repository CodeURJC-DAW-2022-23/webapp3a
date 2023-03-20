package es.webapp3.movieframe.controller;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.webapp3.movieframe.model.Director;
import es.webapp3.movieframe.model.Movie;
import es.webapp3.movieframe.model.Review;
import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.service.MovieService;
import es.webapp3.movieframe.service.ReviewService;
import es.webapp3.movieframe.service.DirectorService;
import es.webapp3.movieframe.service.UserService;

@RestController
public class movieController {
    
    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private DirectorService directorService;
	
    @GetMapping("/movies")
    public Page<Movie> getMovies(Model model,Pageable page){

        return movieService.findAll(page);
    }

    @GetMapping("/reviewsList")
    public Page<Review> getReviews(Model model,Pageable page){

        return reviewService.findAll(page);
    }

    @GetMapping("/usersList")
    public Page<User> getUsers(Model model,Pageable page){

        return userService.findAll(page);
    }

    @GetMapping("/userReviewsList/{id}")
    public ResponseEntity<List<Review>> getUserReviews(Model model,@PathVariable Long id){

        Optional<User> user = userService.findById(id);

        if(user.isPresent()){
            return ResponseEntity.ok(user.get().getReviews());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {

        Optional<Movie> movie = movieService.findById(id);

        if(movie.isPresent()){
            return ResponseEntity.ok(movie.get());
        }else{
            return ResponseEntity.notFound().build();
        }   
    }

    @DeleteMapping("/reviewsList/{id}")
    public ResponseEntity<Review> deleteReviewById(Model model,@PathVariable Long id) {

        Optional<Review> review = reviewService.findById(id);

        if(review.isPresent()){
            reviewService.deleteById(id);
            return ResponseEntity.ok(review.get());
        }else{
            return ResponseEntity.notFound().build();
        }   
    }

    @PutMapping("/user/reviews/{id}")
    public ResponseEntity<Review> editReview(@PathVariable long id,@RequestBody Review newReview) {

        Optional<Review> review = reviewService.findById(id);

        if (review.isPresent()) {
            newReview.setId(id);
            reviewService.save(newReview);
            return ResponseEntity.ok(newReview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/directors/{id}")
    public ResponseEntity<Director> getDirector(@PathVariable long id){
        Optional<Director> director = directorService.findById(id);
        return ResponseEntity.ok(director.get());
    }
     
    /*@PostMapping("/newsMovie")
    public ResponseEntity<Movie> createNewsMovie(@RequestBody Movie movie,@PathVariable Long id) {

        movieService.save(movie);
        

        URI location = fromCurrentRequest().path("/newsMovie")
        .buildAndExpand(movie.getId()).toUri();

        return ResponseEntity.created(location).body(movie);
    }*/

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
    @PostMapping("/movies/{id}/review/new")
    public ResponseEntity<Review> newReview(Model model,@PathVariable Long id,@RequestBody Review review){

        Optional<Movie> movie = movieService.findById(id);

        movie.get().setReview(review);

        movieService.save(movie.get());

        URI location = fromCurrentRequest().path("/movies/{id}/review/new")
        .buildAndExpand(review.getId()).toUri();

        return ResponseEntity.created(location).body(review); 
    }

    @PostMapping("/movies/addition/new")
    public ResponseEntity<Movie> newMovie(Model model,Movie movie,MultipartFile imageFile) throws IOException {
        
        movie.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));

        movieService.save(movie);

        URI location = fromCurrentRequest().path("/movies/addition/new")
        .buildAndExpand(movie.getId()).toUri();

        return ResponseEntity.created(location).body(movie); 
    }
    
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
