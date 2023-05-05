package es.webapp3.movieframe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import es.webapp3.movieframe.model.Review;
import es.webapp3.movieframe.model.User;

public interface ReviewRepository extends JpaRepository<Review,Long>{

    //List<Review> findByUser(String author);
    Page<Review> findAll(Pageable page);

    Page<Review> findByUser(User user, Pageable page);
}
