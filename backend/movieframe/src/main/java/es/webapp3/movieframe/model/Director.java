package es.webapp3.movieframe.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Director {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    private String director;

    
    private String biography;

    
    private String name;

    
    private String born;

    
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> genres;

    
    private String residence;

    
    private Double score;

	
    @Lob
    private Blob img;

	
    @ManyToMany
    @JsonIgnore
    private List<Movie> movies = new ArrayList<>();

    public Director(){}

    public Director(String director,String name,String born,String residence,Double score,String... genre){
        super();
        //this.user=author;
        this.director=director;
        //this.biography=biography;
        this.name=name;
        this.born=born;
        this.genres=List.of(genre);
        this.residence=residence;
        this.score=score;
    }

    public void setId(Long id){
        this.setId(id);
    }

    public Long getId(){
        return id;
    }

    public Blob getImageFile(){
        return img;
    }

    public void setImageFile(Blob image){
        this.img=image;
    }

    public void setDirector(String director){
        this.director=director;
    }

    public String getDirector(){
        return director;
    }

    public void setBiography(String biography){
        this.biography=biography;
    }

    public String getBiography(){
        return biography;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setBorn(String born){
        this.born=born;
    }

    public String getBorn(){
        return born;
    }
    
    public void setGenre(String genre){
        this.genres.add(genre);
    }

    public List<String> getGenre(){
        return genres;
    }

    public void setResidence(String residence){
        this.residence=residence;
    }

    public String getResidence(){
        return residence;
    }

    public void setScore(Double score){
        this.score=score;
    }

    public double getScore(){
        return score;
    }

    public void setMovies(List<Movie> movieList){
        this.movies = movieList;
    }

    public void removeMovie(Movie movie){
        this.movies.remove(movie);
    }

    public List<Movie> getMovies(){
        return movies;
    }
}
