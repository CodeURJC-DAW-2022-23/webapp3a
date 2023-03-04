package es.webapp3.movieframe.model;

import jakarta.persistence.*;

@Entity
public class Review {
    

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private int rating;
    private String coments; 
    private String title;
    private Movie movie;

    public Review(){}

    public Review(String author,int rating,String coments,String title){
        super();
        this.author=author;
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
        this.author=author;
    }

    public String getAuthor(){
        return author;
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
