package es.webapp3.movieframe.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.repository.ReviewRepository;
import es.webapp3.movieframe.model.Review;

@Service
public class ReviewService {

    @Autowired
	private ReviewRepository reviewsRepository;

    private AtomicLong nextId = new AtomicLong(1);

	/*public List<Review> findUserReviews(User user) {
        return reviewsRepository.findByUser(user.getUsername());
	}*/

	public List<Review> findReviews() {
        return reviewsRepository.findAll();
	}

	public Optional<Review> findReview(Long id) {
        return reviewsRepository.findById(id);
	}

	public void saveReview(Review review) {

		reviewsRepository.save(review);
	}

	public void deleteReview(long id) {
        reviewsRepository.deleteById(id);
	}
    
}
