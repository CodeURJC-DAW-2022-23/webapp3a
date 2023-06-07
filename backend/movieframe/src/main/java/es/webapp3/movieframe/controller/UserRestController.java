package es.webapp3.movieframe.controller;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.webapp3.movieframe.model.Review;
import es.webapp3.movieframe.model.User;

import es.webapp3.movieframe.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;
    
    @Operation(summary = "Post user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Posted user", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
        }),
        @ApiResponse(responseCode = "404", description = "No user posted", content = @Content)
    })
    @PostMapping("/api/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> newUser(@RequestBody User user){

        User userSaved = userService.saveAPI(user);  
        if(userSaved != null){
            return new ResponseEntity<>(null,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User updated", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No movie with this id was found to update", content = @Content)
    })
    @PutMapping("/api/users/{userName}")
    public ResponseEntity<User> movieUpdating(@RequestBody User newUser,@PathVariable String userName) {

        Optional<User> user = userService.findByUsername(userName);

        if (user.isPresent()) {
            newUser.setRoles(user.get().getRoles());
            for(Review rv: user.get().getReviews()){
                newUser.setReview(rv);
            }
            if(newUser.getUsername() == null){
                newUser.setUsername(user.get().getUsername());
            }
            if(newUser.getEncodedPassword() == null){			
                newUser.setEncodedPassword(user.get().getEncodedPassword());
            }
            if(newUser.getImageFile() == null){
                newUser.setImageFile(user.get().getImageFile());
            }
            if(newUser.getName() == null){
                newUser.setName(user.get().getName());
            }
            if(newUser.getEmail() == null){
                newUser.setEmail(user.get().getEmail());
            }
            newUser.setId(user.get().getId());
            userService.update(userName,newUser);
            return new ResponseEntity<>(null,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get current user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found users", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
        }),
        @ApiResponse(responseCode = "404", description = "No user founded", content = @Content)
    })
    @GetMapping("/api/users/current")
	public ResponseEntity<User> getCurrentUser(HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();

        User currentUser = userService.findByUsername(principal.getName()).orElseThrow();

        User user = new User();
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        user.setEncodedPassword("");
        user.setRoles(currentUser.getRoles());
        user.setName(currentUser.getName());
        user.setEmail(currentUser.getEmail());
        user.setImageFile(currentUser.getImageFile());

		return ResponseEntity.ok(user);
	}

    @Operation(summary = "Post user image")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Posted user image", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No movie with this id was found to post it an image", content = @Content)
    })
    @PostMapping("/api/users/{id}/image") 
    public ResponseEntity<Object> uploadUserImageAPI(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException {

        Optional<User> user = userService.findById(id);
     
        URI location = fromCurrentRequest().build().toUri();

        user.get().setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));

        userService.saveUpdateAPI(user.get());

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Get user image")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found user image", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No movie image with this id was found", content = @Content)
    })
    @GetMapping("/api/users/{userName}/image")  
    public ResponseEntity<Object> downloadUserImageAPI(@PathVariable String userName) throws SQLException {
        
        Optional<User> user = userService.findByUsername(userName);
        
        if (user.isPresent() && user.get().getImageFile() != null) {
            Resource file = new InputStreamResource(user.get().getImageFile().getBinaryStream());
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .contentLength(user.get().getImageFile().length())
                .body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users/{userName}/image")  
    public ResponseEntity<Object> downloadUserImage(@PathVariable String userName) throws SQLException {
        
        Optional<User> user = userService.findByUsername(userName);
        
        if (user.isPresent() && user.get().getImageFile() != null) {
            Resource file = new InputStreamResource(user.get().getImageFile().getBinaryStream());
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .contentLength(user.get().getImageFile().length())
                .body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}