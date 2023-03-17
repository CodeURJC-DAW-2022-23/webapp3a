package es.webapp3.movieframe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import es.webapp3.movieframe.model.Movie;
import es.webapp3.movieframe.model.Review;

public interface ReviewRepository extends JpaRepository<Review,Long>{

    //List<Review> findByUser(String author);
    Page<Review> findAll(Pageable page);
}
