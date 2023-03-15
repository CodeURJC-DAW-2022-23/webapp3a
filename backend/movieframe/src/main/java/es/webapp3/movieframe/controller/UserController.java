package es.webapp3.movieframe.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.service.ImageService;
import es.webapp3.movieframe.service.UserSession;
import es.webapp3.movieframe.service.UserService;

@RestController
public class UserController {

    private static final String USERS_FOLDER = "users";

    @Autowired
    private UserSession usersession;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageservice;

    @PostMapping("/user/new")
	public String newPost(Model model, User user, MultipartFile image) throws IOException {

		userService.saveUser(user);
		
        usersession.setUser(user);
		imageservice.saveImage(USERS_FOLDER, user.getId(), image);

		return "initial_screen";
	}

    @GetMapping("/post/{id}/image")	
	public ResponseEntity<Object> downloadImage(@PathVariable int id) throws MalformedURLException {

		return imageservice.createResponseFromImage(USERS_FOLDER, id);		
	}

}
