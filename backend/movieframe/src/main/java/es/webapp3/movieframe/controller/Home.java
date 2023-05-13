package es.webapp3.movieframe.controller;

import static org.mockito.Mockito.description;

import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.webapp3.movieframe.model.Director;
import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.service.MovieService;
import es.webapp3.movieframe.service.UserService;

@Controller
public class Home{


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

    @GetMapping("/")
    public String home(Model model,Pageable page){        
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
    public String newUser(Model model,User user, @PathVariable Long id, @RequestParam String username,@RequestParam String name,@RequestParam String email,@RequestParam String password,MultipartFile picture, HttpServletRequest request) throws IOException{

        if(request.isUserInRole("ADMIN") || request.isUserInRole("USER")){

            Optional<User> usr = userService.findById(id);

            if (usr.isPresent()) {

                //Movi      user = new Movie();

                user.setUsername(username);
                user.setName(name);
                user.setMail(email);
                user.setEncodedPassword(passwordEncoder.encode(password)); 
                user.setRoles(usr.get().getRoles());
                user.setImageFile(BlobProxy.generateProxy(picture.getInputStream(), picture.getSize()));
             

                user.setId(id);
                userService.save(user);

                model.addAttribute("picture",user.getImageFile());
                model.addAttribute("username",user.getUsername());
                model.addAttribute("name",user.getName());
                model.addAttribute("email",user.getEmail());

                model.addAttribute("state","user's info updated");

            } else {
                user.setUsername(username);
                user.setName(name);
                user.setMail(email);
                user.setEncodedPassword(password);
                user.setEncodedPassword(passwordEncoder.encode(password)); 
                user.setRoles("USER");
                user.setImageFile(BlobProxy.generateProxy(picture.getInputStream(), picture.getSize()));

                userService.save(user);

                model.addAttribute("state","user registered");


            }
        } 
        return "signup_screen"; 

    }
    
    @GetMapping("/signup")
    public String signup(Model model) { 
        model.addAttribute("state"," ");
        return "signup_screen";
    }

    @RequestMapping("/logout")
    public String logout(Model model) { 
        return "initial_screen";
    }

    @RequestMapping("/loginerror")
    public String loginerror(){
        return "404";
    }
    
}
