package es.webapp3.movieframe.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String name;
    private String email;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    public User(){}

    public User(String username,String password,String name,String mail){
        super();
        this.username=username;
        this.password=password;
        this.name=name;
        this.email=mail;
    }

    public void setId(Long id){
        this.id=id;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setEmail(String mail){
        this.email=mail;
    }

    public Long getId(){
        return id;
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
