package es.webapp3.movieframe.model;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private String movie_img;
    private String movie_category;
    private String movie_title;
    private String movie_votes;
    private String movie_description;

    private List<Review> reviews = new ArrayList<>();

    public Movie(){}

    public Movie(String description,String category,String title,String votes){
        super();
        this.movie_description=description;
        this.movie_category=category;
        this.movie_title=title;
        this.movie_votes=votes;
        
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
        return movie_category;
    }

    public String getTitle(){
        return movie_title;
    }

    public String getVotes(){
        return movie_votes;
    }
}
