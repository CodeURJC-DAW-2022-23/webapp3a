package es.webapp3.movieframe.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.webapp3.movieframe.model.Review;
import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.service.ReviewService;
import es.webapp3.movieframe.service.UserService;

@RestController
public class ReviewRestController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @GetMapping("/reviews/{id}")
    public Page<Review> deleteReviewById(@PathVariable long id, Pageable page) {
        reviewService.deleteById(id);
        return reviewService.findAll(page);
    }

    @GetMapping("/reviews")
    public Page<Review> getReviews(Model model,Pageable page){

        return reviewService.findAll(page);
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
}
