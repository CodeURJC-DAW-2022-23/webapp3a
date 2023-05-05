package es.webapp3.movieframe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import es.webapp3.movieframe.repository.ReviewRepository;
import es.webapp3.movieframe.model.Review;
import es.webapp3.movieframe.model.User;

@Service
public class ReviewService {

    @Autowired
	private ReviewRepository reviewsRepository;

	public Page<Review> findAll(Pageable page) {
        return reviewsRepository.findAll(page);
	}

	public Optional<Review> findById(Long id) {
        return reviewsRepository.findById(id);
	}

	public Page<Review> findByUser(User user, Pageable page) {
        return reviewsRepository.findByUser(user,page);
	}

	public void save(Review review) {

		reviewsRepository.save(review);
	}

	public void deleteById(long id) {
        reviewsRepository.deleteById(id);
	}
    
}
