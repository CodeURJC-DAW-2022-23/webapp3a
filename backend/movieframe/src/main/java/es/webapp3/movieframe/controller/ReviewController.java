package es.webapp3.movieframe.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import es.webapp3.movieframe.model.Review;
import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.service.ReviewService;
import es.webapp3.movieframe.service.UserService;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

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
    
    @GetMapping("/reviews/{userName}")
    public String getUserReviews(Model model,@PathVariable String userName,HttpServletRequest request){    

        if(request.isUserInRole("USER")){
            String name = request.getUserPrincipal().getName();

            userName = name;

            Optional<User> currentUser = userService.findByUsername(name);

            if(!currentUser.get().getReviews().isEmpty()){
                model.addAttribute("reviews",currentUser.get().getReviews());
            }else{
                model.addAttribute("reviews"," ");
            }
            model.addAttribute("userName",name);
            return "reviews_screen";
        } else {
            return "404";
        }
    }

    @DeleteMapping("/reviews/deletion/{id}")
    public String deleteReviewById(Model model,@PathVariable Long id,HttpServletRequest request) {

        if(request.isUserInRole("ADMIN")){
            Optional<Review> review = reviewService.findById(id);

            if(review.isPresent()){
                reviewService.deleteById(id);
                return "reviews_screen";
            }else{
                return "404";
            }   
        } else {
            return "404";
        }
    }

    @GetMapping("/reviews")
    public String getReviews(Model model,Pageable pageable,HttpServletRequest request){

        if(request.isUserInRole("ADMIN")){
            Page<Review> reviews = reviewService.findAll(pageable);

            model.addAttribute("reviews",reviews);

            return "modification_reviews_screen";
        } else {
            return "404";
        }
    }
    
}
