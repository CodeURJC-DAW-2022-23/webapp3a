package es.webapp3.movieframe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.webapp3.movieframe.model.movie;

public interface MovieRepository extends JpaRepository<movie, Long>{
    
}
