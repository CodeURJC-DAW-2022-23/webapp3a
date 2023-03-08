package es.webapp3.movieframe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
/* 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
*/
import es.webapp3.movieframe.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

    List<Movie> findByTitle(String title);

    List<Movie> findByGender(String gender);
    
}
