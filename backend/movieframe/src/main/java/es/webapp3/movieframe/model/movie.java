package es.webapp3.movieframe.model;
import java.sql.Blob;

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
    String title;
    String director;
    String gender;

    @Column(columnDefinition = "TEXT")
    String description;

    @Lob
	private Blob imageFile;

	private boolean image;
    
    public movie(){}

    public movie( String title, String director, String gender, String description) {
        this.title = title;
        this.director = director;
        this.gender = gender;
        this.description = description;
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
    
}
