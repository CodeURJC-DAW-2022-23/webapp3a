package es.webapp3.movieframe.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


@Entity
public class Movie implements Serializable{

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String title;
    private String gender;
    private String movie_description;
    private String movie_img;
    private int movie_votes;
    private String trailer;
    
    private List<Review> reviews = new ArrayList<>();

    public Movie(){}

    public Movie(String title,String category,String description,String img,int votes,String spoiler){
        super();
        this.movie_description=description;
        this.gender=category;
        this.title=title;
        this.movie_votes=votes;
        this.trailer=spoiler;
        
    }

    public List<Review> getReviews(){
        return reviews;
    }

    public void setDescription(String descript){
        this.movie_description=descript;
    }

    public String getDescription(){
        return movie_description;
    }

    public String getImg(){
        return movie_img;
    }

    public String getCategory(){
        return gender;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getTitle(){
        return title;
    }

    public int getVotes(){
        return movie_votes;
    }

    public String getTrailer(){
        return trailer;
    }
}
