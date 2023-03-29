package es.webapp3.movieframe.controller;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

    @Operation(summary = "Get movies")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found movies list", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))
        }),
        @ApiResponse(responseCode = "404", description = "No movie founded", content = @Content)
    })
    @GetMapping("/api/movies")
    public Page<Movie> getMovies(Model model,Pageable page){

        return movieService.findAll(page);
    }

    @Operation(summary = "Get movies by a name introduced in a dialog box")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found movies", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))
        }),
        @ApiResponse(responseCode = "404", description = "No movie matches this name", content = @Content)
    })
    @GetMapping("/api/movies/name")
    public Page<Movie> searchMovie(@RequestBody Movie movie,Pageable page){      

        return movieService.findByTitle(movie.getTitle(),page);      
    }

    @Operation(summary = "Get reviews")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found reviews list", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class))
        }),
        @ApiResponse(responseCode = "404", description = "No review founded", content = @Content)
    })
    @GetMapping("/api/reviewsList")
    public Page<Review> getReviews(Model model,Pageable page){

        return reviewService.findAll(page);
    }

    @Operation(summary = "Get users")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found users", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
        }),
        @ApiResponse(responseCode = "404", description = "No user founded", content = @Content)
    })
    @GetMapping("/api/usersList")
    public Page<User> getUsers(Model model,Pageable page){

        return userService.findAll(page);
    }

    @Operation(summary = "Get the currently logged user reviews")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found user reviews", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid username supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No review posted", content = @Content)
    })
    @GetMapping("/api/userReviewsList/{userName}")
    public ResponseEntity<List<Review>> getUserReviews(Model model,@PathVariable String userName){

        Optional<User> user = userService.findByUsername(userName);

        if(user.isPresent()){
            return new ResponseEntity<>(user.get().getReviews(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get movie")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found movie", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No movie with this id was found", content = @Content)
    })
    @GetMapping("/api/movies/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {

        Optional<Movie> movie = movieService.findById(id);

        if(movie.isPresent()){
            return new ResponseEntity<>(movie.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }   
    }

    @Operation(summary = "Get review")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found review", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No review with this id was found", content = @Content)
    })
    @GetMapping("/api/reviews/{id}")
    public ResponseEntity<Review> getReview(@PathVariable Long id) {

        Optional<Review> review = reviewService.findById(id);

        if(review.isPresent()){
            return new ResponseEntity<>(review.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }   
    }

    @Operation(summary = "Delete review")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Deleted review", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No review with this id was found to delete", content = @Content)
    })
    @DeleteMapping("/api/reviewsList/{id}")
    public ResponseEntity<Review> deleteReviewById(Model model,@PathVariable Long id) {

        Optional<Review> review = reviewService.findById(id);

        if(review.isPresent()){
            reviewService.deleteById(id);
            return new ResponseEntity<>(review.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }   
    }

    @Operation(summary = "Update movie")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated movie", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No movie with this id was found to update", content = @Content)
    })
    @PutMapping("/api/movies/{id}/edition")
    public ResponseEntity<Movie> movieUpdating(Model model,@RequestBody Movie newMovie,@PathVariable Long id) {

        Optional<Movie> movie = movieService.findById(id);

        if (movie.isPresent()) {

            newMovie.setId(id);
            movieService.save(newMovie);

            return new ResponseEntity<>(newMovie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @Operation(summary = "Get director")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found director", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Director.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No director with this id was found", content = @Content)
    })
    @GetMapping("/api/directors/{id}")
    public ResponseEntity<Director> getDirector(@PathVariable long id){

        Optional<Director> director = directorService.findById(id);

        if(director.isPresent()){
            return new ResponseEntity<>(director.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @Operation(summary = "Post review into a movie")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Posted review", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No movie with this id was found to post a review", content = @Content)
    })
    @PostMapping("/api/movies/{id}/review/new")
    public ResponseEntity<Review> newReview(Model model,@PathVariable Long id,@RequestBody Review review){

        Optional<Movie> movie = movieService.findById(id);

        movie.get().setReview(review);

        movieService.save(movie.get());

        URI location = fromCurrentRequest().path("/{id}")
        .buildAndExpand(review.getId()).toUri();

        return ResponseEntity.created(location).body(review); 
    }

    @Operation(summary = "Post movie")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Posted movie", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))
        }),
        @ApiResponse(responseCode = "404", description = "No movie posted", content = @Content)
    })
    @PostMapping("/api/movies/addition/new")
    public ResponseEntity<Movie> newMovie(@RequestBody Movie movie) {

        movieService.save(movie);

        URI location = fromCurrentRequest().path("/api/movies/addition/new/{id}")
        .buildAndExpand(movie.getId()).toUri();

        return ResponseEntity.created(location).body(movie); 
    }

    @Operation(summary = "Post user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Posted user", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
        }),
        @ApiResponse(responseCode = "404", description = "No user posted", content = @Content)
    })
    @PostMapping("/api/users/new")
    public ResponseEntity<User> newUser(@RequestBody User user){

        userService.save(user);  

        URI location = fromCurrentRequest().path("/api/users/new/{id}")
        .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).body(user);
    }

    @Operation(summary = "Post movie image")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Posted movie image", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No movie with this id was found to post it an image", content = @Content)
    })
    @PostMapping("/api/movies/addition/new/{id}/image")
    public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException {

        Optional<Movie> movie = movieService.findById(id);
     
        URI location = fromCurrentRequest().build().toUri();

        movie.get().setImage(true);

        movie.get().setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));

        movieService.save(movie.get());

        return ResponseEntity.created(location).build();
    }
    
    @Operation(summary = "Get movie image")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found movie image", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No movie image with this id was found", content = @Content)
    })
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
    
    @Operation(summary = "Get director image")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found director image", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Director.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No director image with this id was found", content = @Content)
    })
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
