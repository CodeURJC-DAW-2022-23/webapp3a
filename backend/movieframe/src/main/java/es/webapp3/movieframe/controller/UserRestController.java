package es.webapp3.movieframe.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.webapp3.movieframe.model.Review;
import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.service.ReviewService;
import es.webapp3.movieframe.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class UserRestController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get users")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found users", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
        }),
        @ApiResponse(responseCode = "404", description = "No user founded", content = @Content)
    })
    @GetMapping("/api/usersList")
    public Page<User> getUsers(Model model,Pageable page){

        return userService.findAll(page);
    }
    
    @Operation(summary = "Post user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Posted user", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
        }),
        @ApiResponse(responseCode = "404", description = "No user posted", content = @Content)
    })
    @PostMapping("/api/users/new")
    @ResponseStatus(HttpStatus.CREATED)
    public User newUser(@RequestBody User user){

        userService.save(user);  

        return user;
    }

    @GetMapping("/userReviewsList/{userName}")
    public ResponseEntity<Page<Review>> getUserReviews(Model model,@PathVariable String userName,Pageable page){

        Optional<User> user = userService.findByUsername(userName);

        if(user.isPresent()){
            
            return ResponseEntity.ok(reviewService.findByUser(user.get(), page));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/Users/current")
	public ResponseEntity<User> getCurrentUser(HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		
		if(principal != null) {
			return ResponseEntity.ok(userService.findByUsername(principal.getName()).orElseThrow());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}