package es.webapp3.movieframe.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Review{   

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int rating;
    private String coments;

    @ManyToOne
    @JsonIgnore
    private Movie movie;

    @ManyToOne
    @JsonIgnore
    private User user;

    public Review(){}

    public Review(int rating,String coments){
        super();
        this.rating=rating;
        this.coments=coments;
    }

    public void setId(Long id){
        this.setId(id);
    }

    public Long getId(){
        return id;
    }


    public void setRating(int rating){
        this.rating = rating;
    }

    public int getRating(){
        return rating;
    }

    public void setComent(String coment){
        this.coments=coment;
    }

    public String getComents(){
        return coments;
    }

    public void setMovie(Movie movie){
        this.movie=movie;
    }

    public Movie getMovie(){
        return movie;
    }

    public void setUser(User user){
        this.user=user;
    }

    public User getUser(){
        return user;
    }
}
