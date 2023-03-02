package es.webapp3.movieframe.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import es.webapp3.movieframe.model.Review;
import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.service.UsersService;

@Controller
public class UserController {

    @Autowired
    private User user;

    @Autowired
    private UsersService usersService;

    List<User> users = new ArrayList<>();

    @PostMapping("/review/new")
	public void newReview(Model model, Review review,@RequestParam String rating, @RequestParam String coments) {
        review.setRating(rating);
        review.setComent(coments);
        review.setAuthor(user.getUsername());
        user.getReviews().add(review);
        usersService.save(user, review);
	}

    @GetMapping("/reviews")
    public String showReviews(Model model){
        model.addAttribute("reviews",usersService.findAll());
        return "modification_reviews_screen.html";
    }

    @GetMapping("/{user}")
    public String showUserReviews(Model model,User user){
         model.addAttribute("reviews",usersService.findByAuthor(user));

         return "reviews_screen.html";
    }

    @GetMapping("/review/{author}/{id}/delete")
	public void deleteReview(Model model, @PathVariable int id, @PathVariable String author) {
        for(User us: users){
            if(us.getUsername().equals(author)){
                usersService.deleteById(us, id);
            }
        }
		

	}
    
}
