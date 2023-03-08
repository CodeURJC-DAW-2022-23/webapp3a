package es.webapp3.movieframe.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
public class Review implements Serializable{
    

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String user;
    private int rating;
    private String coments; 
    private String title;
    private Movie movie;

    public Review(){}

    public Review(String author,int rating,String coments,String title){
        super();
        this.user=author;
        this.rating=rating;
        this.coments=coments;
        this.title=title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getTitle(){
        return title;
    }

    public void setId(Long id){
        this.setId(id);
    }

    public Long getId(){
        return id;
    }

    public Movie getMovie(){
        return movie;
    }

    public void setAuthor(String author){
        this.user=author;
    }

    public String getAuthor(){
        return user;
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
}
