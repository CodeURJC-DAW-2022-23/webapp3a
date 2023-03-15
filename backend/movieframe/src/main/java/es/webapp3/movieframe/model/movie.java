package es.webapp3.movieframe.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class Movie{


    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    private String title;  
    private String gender;

    
    @Column(columnDefinition = "TEXT")
    private String movie_description;

    @Lob 
    private Blob movie_img;
    
    
    private int movie_votes;


    @OneToMany(mappedBy="movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

   
    @ManyToMany(mappedBy="movies")
    private List<Director> directors = new ArrayList<>();

    public Movie(){}

    public void setReview(Review review){
        reviews.add(review);
        review.setMovie(this);
    }

    public void removeReview(Review review){
        reviews.remove(review);
        review.setMovie(null);
    }

    public List<Review> getReviews(){
        return reviews;
    }

    public void removeDirector(Director director){
        this.directors.remove(director);
        director.removeMovie(this);
    }

    public void setDirectors(List<Director> movieDirectors){
        this.directors = movieDirectors;
    }

    public List<Director> getDirectors(){
        return directors;
    }

    public void setDescription(String descript){
        this.movie_description=descript;
    }

    public String getDescription(){
        return movie_description;
    }

    public Blob getImageFile(){
        return movie_img;
    }

    public void setImageFile(Blob image){
        this.movie_img=image;
    }

    public void setCategory(String gender){
        this.gender=gender;
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

    public void setVotes(int votes){
        this.movie_votes=votes;
    }

    public int getVotes(){
        return movie_votes;
    }

}
