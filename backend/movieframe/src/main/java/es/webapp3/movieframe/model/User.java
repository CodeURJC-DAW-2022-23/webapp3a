package es.webapp3.movieframe.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import java.sql.Blob;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User{

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String encodedPassword;

    private String name;
    private String email;
    private String roles;

    @Lob 
    private Blob avatar;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public User(){}

    public User(String username, String encodedPassword, String roles){
        this.username=username;
        this.encodedPassword=encodedPassword;
        this.roles=roles;
    }

    public void setReview(Review review){
        reviews.add(review);
        review.setUser(this);
    }

    public void removeReview(Review review){
        reviews.remove(review);
        review.setUser(null);
    }

    public List<Review> getReviews(){
        return reviews;
    }
    
    public void setId(Long id){
        this.id=id;
    }

    public Long getId(){
        return id;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public String getUsername(){
        return username;
    }

    public void setEncodedPassword(String encodedPassword){
        this.encodedPassword=encodedPassword;
    }

    public String getEncodedPassword(){
        return encodedPassword;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getEmail(){
        return email;
    }

    public void setRoles(String rol){
        this.roles=rol;
    }

    public String getRoles(){
        return roles;
    }

    public Blob getImageFile(){
        return avatar;
    }

    public void setImageFile(Blob image){
        this.avatar=image;
    }

  
}
