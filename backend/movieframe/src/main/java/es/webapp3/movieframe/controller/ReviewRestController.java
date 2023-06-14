package es.webapp3.movieframe.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.webapp3.movieframe.model.Movie;
import es.webapp3.movieframe.model.Review;
import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.service.MovieService;
import es.webapp3.movieframe.service.ReviewService;
import es.webapp3.movieframe.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class ReviewRestController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @GetMapping("/reviews/{id}")
    public Page<Review> deleteReviewById(@PathVariable long id, Pageable page) {
        reviewService.deleteById(id);
        return reviewService.findAll(page);
    }

    @Operation(summary = "Get reviews")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found reviews list", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class))
        }),
        @ApiResponse(responseCode = "404", description = "No review founded", content = @Content)
    })
    @GetMapping("/api/reviews")
    public Page<Review> getReviewsAPI(Model model,Pageable page){

        return reviewService.findAll(page);
    }

    @GetMapping("/reviews")
    public Page<Review> getReviews(Model model,Pageable page){

        return reviewService.findAll(page);
    }

    @Operation(summary = "Get the currently logged user reviews")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found user reviews", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid username supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No review posted", content = @Content)
    })
    @GetMapping("/api/reviews/user/{userName}")
    public ResponseEntity<Page<Review>> getUserReviewsAPI(Model model,@PathVariable String userName,Pageable page){

        Optional<User> user = userService.findByUsername(userName);

        if(user.isPresent()){
            return new ResponseEntity<>(reviewService.findByUser(user.get(),page),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reviews/user/{userName}")
    public ResponseEntity<Page<Review>> getUserReviews(Model model,@PathVariable String userName,Pageable page){

        Optional<User> user = userService.findByUsername(userName);

        if(user.isPresent()){
            return new ResponseEntity<>(reviewService.findByUser(user.get(),page),HttpStatus.OK);
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
    @DeleteMapping("/api/reviews/{id}")
    public ResponseEntity<Review> deleteReviewById(Model model,@PathVariable Long id) {

        Optional<Review> review = reviewService.findById(id);

        if(review.isPresent()){
            reviewService.deleteById(id);
            return new ResponseEntity<>(review.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }   
    }

    @Operation(summary = "Get one review")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "review", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No review with this id was found to delete", content = @Content)
    })
    @GetMapping("/api/reviews/{id}")
    public ResponseEntity<Review> getReviewById(Model model,@PathVariable Long id) {

        Optional<Review> review = reviewService.findById(id);

        if(review.isPresent()){
            return new ResponseEntity<>(review.get(), HttpStatus.OK);
        }else{
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
    @PostMapping("/api/movies/{id}/review/{userName}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Review> newReviewAPI(@PathVariable Long id,@PathVariable String userName,@RequestBody Review review){

        Optional<Movie> movie = movieService.findById(id);
        Optional<User> user = userService.findByUsername(userName);

        review.setMovie(movie.get());
        review.setUser(user.get());

        return new ResponseEntity<>(reviewService.save(review), HttpStatus.OK);
    }
}
