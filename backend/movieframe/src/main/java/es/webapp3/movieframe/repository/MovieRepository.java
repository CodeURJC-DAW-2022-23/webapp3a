package es.webapp3.movieframe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/* 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
*/
import es.webapp3.movieframe.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
    @Query("SELECT m from Movie m where m.title like ?1")
    List<Movie> findByTitle(String title);

    Movie findSingleByTitle(String gender);

    List<Movie> findByGender(String gender);
}
