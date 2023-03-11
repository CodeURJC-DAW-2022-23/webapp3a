package es.webapp3.movieframe.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.repository.ReviewRepository;
import jakarta.annotation.PostConstruct;
import es.webapp3.movieframe.model.Review;

@Service
public class ReviewService {

    @Autowired
	private ReviewRepository reviewsRepository;

	@PostConstruct
	public void init() {

        reviewsRepository.save(new Review("edwardkennedy",3,"Africa's burgeoning talents from across the continent ...","Godzilla: King Of The Monsters Adds O’Shea Jackson Jr"));
        reviewsRepository.save(new Review("edwardkennedy",5,"Magnolia Pictures has acquired U.S. the...","Magnolia Nabs ‘Lucky’ Starring Harry Dean Stanton"));
        reviewsRepository.save(new Review("hughjackman",4,"New Line’s remake of “Going in Style” launched with a moderate $...","‘Going in Style’ Tops ‘Smurfs: The Lost Village’ at Thursday Box Office"));		

        for(int i=0; i<10; i++) {
			reviewsRepository.save(new Review("Author"+i, i, "Coments"+i, "Title"+i));
		}
    }

    private AtomicLong nextId = new AtomicLong(1);

	public List<Review> findUserReviews(User user) {
        return reviewsRepository.findByUser(user.getUsername());
	}

	public List<Review> findReviews() {
        return reviewsRepository.findAll();
	}

	public Optional<Review> findReview(Long id) {
        return reviewsRepository.findById(id);
	}

	public void saveReview(Review review) {

		long id = nextId.getAndIncrement();

		review.setId(id);

		reviewsRepository.save(review);
	}

	public void deleteReview(long id) {
        reviewsRepository.deleteById(id);
	}
    
}
