package es.webapp3.movieframe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.webapp3.movieframe.model.Review;

public interface ReviewRepository extends JpaRepository<Review,Long>{

    Review findReviewById(String author,Long id);

    void saveReview(String author,Review review);
    
    void deleteReview(String author, Long id);
}
