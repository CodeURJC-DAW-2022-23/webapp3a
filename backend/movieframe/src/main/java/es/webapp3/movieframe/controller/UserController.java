package es.webapp3.movieframe.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.webapp3.movieframe.model.Review;
import es.webapp3.movieframe.service.ReviewService;

@Controller
public class UserController {

    @Autowired
    private ReviewService reviewService;

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

    @PutMapping("/reviews/user/edition/{id}")
    public String editReview(Model model,@PathVariable Long id,@RequestParam int rating, @RequestParam String coments,HttpServletRequest request){

        if(request.isUserInRole("USER")){
            Review newReview = new Review(rating,coments);
            newReview.setId(id);

            reviewService.save(newReview);
            
            return "reviews_screen";
        } else {
            return "404";
        }
    }

}
