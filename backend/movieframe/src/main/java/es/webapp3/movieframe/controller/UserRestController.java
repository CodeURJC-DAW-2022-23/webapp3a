package es.webapp3.movieframe.controller;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import es.webapp3.movieframe.model.User;

import es.webapp3.movieframe.service.UserService;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

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
