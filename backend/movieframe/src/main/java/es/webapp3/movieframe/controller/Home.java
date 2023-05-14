package es.webapp3.movieframe.controller;


import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.webapp3.movieframe.model.User;
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
    public String newUser(Model model,User user, @RequestParam String username,@RequestParam String name,@RequestParam String email,@RequestParam String password,MultipartFile image1, HttpServletRequest request) throws IOException, SQLException{

                user.setUsername(username);
                user.setName(name);
                user.setMail(email);
                user.setEncodedPassword(password);
                user.setEncodedPassword(passwordEncoder.encode(password)); 
                user.setRoles("USER");
                if(!image1.isEmpty()){
                    user.setImageFile(BlobProxy.generateProxy(image1.getInputStream(), image1.getSize()));
                   
                }
                userService.save(user);

                model.addAttribute("username",user.getUsername());
                model.addAttribute("name",user.getName());
                model.addAttribute("email",user.getEmail());
                model.addAttribute("state","user registered");
        
        return "signup_screen"; 

    }

    @PostMapping("/user/{userName}/edition")
    public String userProfileEdition(Model model,@PathVariable String userName, @RequestParam String username,@RequestParam String name,@RequestParam String email,@RequestParam String password,MultipartFile image1, HttpServletRequest request) throws IOException{

        if(request.isUserInRole("ADMIN") || request.isUserInRole("USER")){

            Optional<User> oldUser = userService.findByUsername(userName);

            if (oldUser.isPresent()) {

                User user = new User(username,passwordEncoder.encode(password),oldUser.get().getRoles());

                user.setName(name);
                user.setMail(email);                

                user.setId(oldUser.get().getId());
                user.setImageFile(BlobProxy.generateProxy(image1.getInputStream(), image1.getSize()));
                userService.save(user);

                model.addAttribute("picture",user.getImageFile());
                model.addAttribute("username",user.getUsername());
                model.addAttribute("name",user.getName());
                model.addAttribute("email",user.getEmail());

                model.addAttribute("state","user's info updated");
                
            } else {
                return "404";
            }
            return "user_profile_screen";
        } else {
            return "404";
        }
    }

    @GetMapping("/user/{userName}")
    public String userProfile(Model model,@PathVariable String userName,HttpServletRequest request){

        if(request.isUserInRole("ADMIN") || request.isUserInRole("USER")){

            Optional<User> user = userService.findByUsername(userName);

            if (user.isPresent()) {
                model.addAttribute("picture",user.get().getImageFile());
                model.addAttribute("username",user.get().getUsername());
                model.addAttribute("name",user.get().getName());
                model.addAttribute("email",user.get().getEmail());

                model.addAttribute("state"," ");
                
            } else {
                return "404";
            }
            return "user_profile_screen";
        } else {
            return "404";
        } 
    }
    
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("username", "");
        model.addAttribute("name"," ");
        model.addAttribute("email"," "); 
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
