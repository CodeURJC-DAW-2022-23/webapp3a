package es.webapp3.movieframe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/* 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
*/
import es.webapp3.movieframe.model.movie;

public interface MovieRepository extends JpaRepository<movie, Long>{

    
    @Query("select m from movie m where m.title like %:title%")
    movie findSingleByTitle(String title);

    /* 
    @Query("SELECT m FROM Movie m WHERE m.gender = :gender")
    Page<movie> findByTitle(String gender, Pageable pageable);
    */
}
