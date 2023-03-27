package es.webapp3.movieframe.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.service.MovieService;
import es.webapp3.movieframe.service.UserService;

@Controller
public class home{

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @Autowired
	private PasswordEncoder passwordEncoder;

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

    @GetMapping("/news")
    public String showRecommendationScreen(HttpServletRequest request){
        if(request.isUserInRole("ADMIN")){
            return "recommendations_screen";
        }else{
            return "404";        
        }
    }

    @GetMapping("/")
    public String home(Model model,Pageable page){        
        model.addAttribute("movieframe", movieService.findAll(page));
        return "initial_screen";
    }

    @GetMapping("/director")
    public String showDirector(){
        return "director_screen";
    }

    @RequestMapping("/login")
    public String login(Model model) {  
        return "login_screen";
    }

    @PostMapping("/signup/new")
    public String newUser(Model model,User user,@RequestParam String username,@RequestParam String name,@RequestParam String email,@RequestParam String password){

        user.setUsername(username);
        user.setName(name);
        user.setMail(email);
        user.setEncodedPassword(password);
        user.setEncodedPassword(passwordEncoder.encode(password)); 
        user.setRoles("USER");

        userService.save(user);

        model.addAttribute("state","user registered");

        return "signup_screen";  
    }
    
    @GetMapping("/signup")
    public String signup(Model model) { 
        model.addAttribute("state"," ");
        return "signup_screen";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "initial_screen";
    }

    @RequestMapping("/loginerror")
    public String loginerror(){
        return "404";
    }
    
}
