package es.webapp3.movieframe.controller;

import java.io.IOException;
import java.net.URI;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class movieRestController {
    
    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private DirectorService directorService;

    @GetMapping("/api/movies")
    public Page<Movie> getMovies(Model model,Pageable page){

        return movieService.findAll(page);
    }

    @GetMapping("/api/movies/name")
    public Page<Movie> searchMovie(@RequestBody Movie movie,Pageable page){      

        return movieService.findByTitle(movie.getTitle(),page);      
    }

    @GetMapping("/api/reviewsList")
    public Page<Review> getReviews(Model model,Pageable page){

        return reviewService.findAll(page);
    }

    @GetMapping("/api/usersList")
    public Page<User> getUsers(Model model,Pageable page){

        return userService.findAll(page);
    }

    @GetMapping("/api/userReviewsList/{userName}")
    public ResponseEntity<List<Review>> getUserReviews(Model model,@PathVariable String userName){

        Optional<User> user = userService.findByUsername(userName);

        if(user.isPresent()){
            return ResponseEntity.ok(user.get().getReviews());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/movies/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {

        Optional<Movie> movie = movieService.findById(id);

        if(movie.isPresent()){
            return ResponseEntity.ok(movie.get());
        }else{
            return ResponseEntity.notFound().build();
        }   
    }

    @GetMapping("/api/reviews/{id}")
    public ResponseEntity<Review> getReview(@PathVariable Long id) {

        Optional<Review> review = reviewService.findById(id);

        if(review.isPresent()){
            return ResponseEntity.ok(review.get());
        }else{
            return ResponseEntity.notFound().build();
        }   
    }

    @DeleteMapping("/api/reviewsList/{id}")
    public ResponseEntity<Review> deleteReviewById(Model model,@PathVariable Long id) {

        Optional<Review> review = reviewService.findById(id);

        if(review.isPresent()){
            reviewService.deleteById(id);
            return ResponseEntity.ok(review.get());
        }else{
            return ResponseEntity.notFound().build();
        }   
    }

    @PutMapping("/api/movies/{id}/edition")
    public ResponseEntity<Movie> movieUpdating(Model model,@RequestBody Movie newMovie,@PathVariable Long id) {

        Optional<Movie> movie = movieService.findById(id);

        if (movie.isPresent()) {

            newMovie.setId(id);
            movieService.save(newMovie);

            return ResponseEntity.ok(newMovie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/api/directors/{id}")
    public ResponseEntity<Director> getDirector(@PathVariable long id){

        Optional<Director> director = directorService.findById(id);

        if(director.isPresent()){
            return ResponseEntity.ok(director.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
     
    @PostMapping("/api/movies/{id}/review/new")
    public ResponseEntity<Review> newReview(Model model,@PathVariable Long id,@RequestBody Review review){

        Optional<Movie> movie = movieService.findById(id);

        movie.get().setReview(review);

        movieService.save(movie.get());

        URI location = fromCurrentRequest().path("/{id}")
        .buildAndExpand(review.getId()).toUri();

        return ResponseEntity.created(location).body(review); 
    }

    @PostMapping("/api/movies/addition/new")
    public ResponseEntity<Movie> newMovie(@RequestBody Movie movie) {

        movieService.save(movie);

        URI location = fromCurrentRequest().path("/api/movies/addition/new/{id}")
        .buildAndExpand(movie.getId()).toUri();

        return ResponseEntity.created(location).body(movie); 
    }

    @PostMapping("/api/users/new")
    public ResponseEntity<User> newUser(@RequestBody User user){

        userService.save(user);  

        URI location = fromCurrentRequest().path("/api/users/new/{id}")
        .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).body(user);
    }

    @PostMapping("/api/movies/addition/new/{id}/image")
    public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException {

        Optional<Movie> movie = movieService.findById(id);
     
        URI location = fromCurrentRequest().build().toUri();

        movie.get().setImage(true);

        movie.get().setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));

        movieService.save(movie.get());

        return ResponseEntity.created(location).build();
    }
    
    @GetMapping("/api/movies/{id}/image")
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
    
    @GetMapping("/api/movies/{id}/director/image")
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
