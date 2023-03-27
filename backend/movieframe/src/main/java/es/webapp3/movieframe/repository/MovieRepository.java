package es.webapp3.movieframe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.webapp3.movieframe.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
    @Query("SELECT m from Movie m where m.title like ?1 order by m.movie_votes desc")
    Page<Movie> findByTitle(String name,Pageable page);

    Movie findSingleByTitle(String gender);

    List<Movie> findByGender(String gender);

    Page<Movie> findAll(Pageable page);

}
