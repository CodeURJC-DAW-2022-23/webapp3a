package es.webapp3.movieframe.model;

import org.springframework.beans.factory.annotation.Autowired;

public class Review {
    
    private Long id;
    private String author;
    private String rating;
    private String coments;
    private String title;

    @Autowired
    private Movie movie;

    public Review(){}

    public Review(String rating,String coments){
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

    public void setTitle(String title){
        this.title=movie.getTitle();
    }

    public String getTitle(){
        return title;
    }

    public void setAuthor(String author){
        this.author=author;
    }

    public String getAuthor(){
        return author;
    }

    public void setRating(String rating){
        this.rating = rating;
    }

    public String getRating(){
        return rating;
    }

    public void setComent(String coment){
        this.coments=coment;
    }

    public String getComents(){
        return coments;
    }
}
