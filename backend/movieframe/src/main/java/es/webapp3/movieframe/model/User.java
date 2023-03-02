package es.webapp3.movieframe.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class User {

    private String username;
    private String password;
    private String name;
    private String email;
    private List<Review> reviews = new ArrayList<>();

    public User(String username,String password,String name,String mail){
        super();
        this.username=username;
        this.password=password;
        this.name=name;
        this.email=mail;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }
    
    public List<Review> getReviews(){
        return reviews;
    }
    
}
