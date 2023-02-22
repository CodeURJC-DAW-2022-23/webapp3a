package es.webapp3.movieframe.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class movie {
    
    @Id 
    String title;
}
