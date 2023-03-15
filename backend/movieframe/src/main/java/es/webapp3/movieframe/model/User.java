package es.webapp3.movieframe.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class User{

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String encodedPassword;
    private String name;
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public User(){}

    public User(String username,String encodedPassword,String name,String mail,String... roles){
        super();
        this.username=username;
        this.encodedPassword=encodedPassword;
        this.name=name;
        this.email=mail;
        this.roles=List.of(roles);
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

    public void setUsername(String username){
        this.username=username;
    }

    public void setEncodedPassword(String password){
        this.encodedPassword=password;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setEmail(String mail){
        this.email=mail;
    }

    public void setRoles(List<String> roles){
        this.roles=roles;
    }

    public Long getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getEncodedPassword(){
        return encodedPassword;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public List<String> getRoles(){
        return roles;
    }
    
}
