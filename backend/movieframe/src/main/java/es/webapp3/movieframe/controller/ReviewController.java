package es.webapp3.movieframe.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.webapp3.movieframe.model.Movie;
import es.webapp3.movieframe.model.Review;
import es.webapp3.movieframe.service.UserSession;
import es.webapp3.movieframe.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private UserSession usersession;

    @Autowired
    private ReviewService reviewService;

    

    @PostMapping("/new")
	public ResponseEntity<Review> newReview(Model model,@PathVariable Movie movie,@RequestBody Review review) {
        //review.setAuthor(usersession.getUser().getUsername());

       

        movie.getReviews().add(review);

        model.addAttribute("review",review);

        reviewService.saveReview(review);

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(review.getId()).toUri();

		return ResponseEntity.created(location).body(review);
	}

    @GetMapping("/")
    public String showReviews(Model model){

            model.addAttribute("reviews",reviewService.findReviews());

            return "modification_reviews_screen";
    }

    @GetMapping("/{user}")
    public String showUserReviews(Model model){

         //model.addAttribute("reviews",reviewService.findUserReviews(usersession.getUser()));

         return "reviews_screen";
    }

    @DeleteMapping("/{id}/delete")
	public String deleteReview(Model model, @PathVariable Long id) {
        Optional<Review> review = reviewService.findReview(id);

        if (review.isPresent()) {
			reviewService.deleteReview(id);
			return "modification_reviews_screen";
		} else {
			return "404";
		}
	}
    
}
