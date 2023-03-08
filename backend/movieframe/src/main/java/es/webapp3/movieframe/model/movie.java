package es.webapp3.movieframe.model;
import java.sql.Blob;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class movie {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    private String title;
    private String director;
    private String gender;
    private String trailer;
    private List<String> actors;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Lob
	private Blob imageFile;

	private boolean image;
    
    @Lob
	private Blob imageTrailer;

	private boolean imageTR;
    
    public movie(){}

    
    
    public movie(String title, String director, String gender, String description, String trailer) {
        this.title = title;
        this.director = director;
        this.gender = gender;
        this.trailer = trailer;
        this.description = description;
    }



    public Blob getImageTrailer() {
        return imageTrailer;
    }



    public void setImageTrailer(Blob imageTrailer) {
        this.imageTrailer = imageTrailer;
    }



    public boolean isImageTR() {
        return imageTR;
    }



    public void setImageTR(boolean imageTR) {
        this.imageTR = imageTR;
    }



    public Long getId() {
        return id;
    }
    
    public Blob getImageFile() {
        return imageFile;
    }

    public void setImageFile(Blob imageFile) {
        this.imageFile = imageFile;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }



    public List<String> getActors() {
        return actors;
    }



    public void setActors(List<String> actors) {
        this.actors = actors;
    }
    
}
