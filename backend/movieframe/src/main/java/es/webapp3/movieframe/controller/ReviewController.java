package es.webapp3.movieframe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import es.webapp3.movieframe.model.Review;
import es.webapp3.movieframe.service.UserSession;
import es.webapp3.movieframe.service.UsersService;

@Controller
public class ReviewController {

    @Autowired
    private UserSession usersession;

    @Autowired
    private UsersService usersService;

    @PostMapping("/review/new")
	public String newReview(Model model, Review review,@RequestParam String rating, @RequestParam String coments) {
        review.setRating(rating);
        review.setComent(coments);
        review.setAuthor(usersession.getUser().getUsername());

        model.addAttribute("user",usersession.getUser().getUsername());

        usersService.save(usersession.getUser(), review);

        return "movie_screen.html";
	}

    @GetMapping("/reviews")
    public String showReviews(Model model){
        model.addAttribute("reviews",usersService.findAll());
        return "modification_reviews_screen.html";
    }

    @GetMapping("/{user}")
    public String showUserReviews(Model model){
         model.addAttribute("reviews",usersService.findByAuthor(usersession.getUser().getUsername()));

         return "reviews_screen.html";
    }

    @GetMapping("/review/{author}/{id}/delete")
	public String deleteReview(Model model, @PathVariable int id, @PathVariable String author) {
        usersService.deleteById(author, id);

        model.addAttribute("reviews",usersService.findAll());

        return "modification_reviews_screen.html";
	}
    
}
