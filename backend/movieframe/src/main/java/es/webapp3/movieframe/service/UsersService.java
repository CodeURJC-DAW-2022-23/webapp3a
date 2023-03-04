package es.webapp3.movieframe.service;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.repository.UserRepository;
import es.webapp3.movieframe.repository.ReviewRepository;
import jakarta.annotation.PostConstruct;
import es.webapp3.movieframe.model.Review;

@Service
public class UsersService {

    @Autowired
	private UserRepository usersRepository;

    @Autowired
	private ReviewRepository reviewsRepository;

	@PostConstruct
	public void init() {
		
		usersRepository.saveUser(new User("edwardKennedy","edu123456","Edward","edward@kennedy.com"));
        usersRepository.saveUser(new User("hughjackman","maninthemiddle","Hugh","hugh@jack.com"));

        reviewsRepository.saveReview("edwardkennedy", new Review("edwardkennedy",3,"Africa's burgeoning talents from across the continent ...","Godzilla: King Of The Monsters Adds O’Shea Jackson Jr"));
        reviewsRepository.saveReview("edwardkennedy", new Review("edwardkennedy",5,"Magnolia Pictures has acquired U.S. the...","Magnolia Nabs ‘Lucky’ Starring Harry Dean Stanton"));
        reviewsRepository.saveReview("hughjackman", new Review("hughjackman",4,"New Line’s remake of “Going in Style” launched with a moderate $...","‘Going in Style’ Tops ‘Smurfs: The Lost Village’ at Thursday Box Office"));
		
		for(int i=0; i<100; i++) {
			usersRepository.saveUser(new User("Username"+i, "Password"+i, "Name"+i, "Mail"+i));
		}

        for(int i=0; i<100; i++) {
			reviewsRepository.saveReview("Author"+i, new Review("Author"+i, i, "Coments"+i, "Title"+i));
		}
    }

    private AtomicLong nextId = new AtomicLong(1);

    public List<User> findAll() {
        return usersRepository.findAllUsers();
	}

    public User findByAuthor(String author) {
        return usersRepository.findReviewByAuthor(author);
	}

	public Review findById(String author,long id) {
        return reviewsRepository.findReviewById(author, id);
	}

	public void save(String user,Review review) {

		long id = nextId.getAndIncrement();

		review.setId(id);

		reviewsRepository.saveReview(user, review);
	}

    public void save(User user) {

		long id = nextId.getAndIncrement();

		user.setId(id);
        usersRepository.saveUser(user);
	}

	public void deleteById(String author, long id) {
        reviewsRepository.deleteReview(author, id);
	}
    
}
