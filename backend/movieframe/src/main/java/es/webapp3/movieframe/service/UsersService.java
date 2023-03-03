package es.webapp3.movieframe.service;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;
import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.model.Review;

@Service
public class UsersService {

    List<User> users = new ArrayList<>();
    List<Review> reviews = new ArrayList<>();

    private AtomicLong nextId = new AtomicLong();

    public List<Review> findAll() {
        for(User us: users){
		    reviews.addAll(us.getReviews());
        }
        return reviews;
	}

    public List<Review> findByAuthor(String author) {
        for(User us: users){
            if(us.getUsername().equals(author)){
		        return us.getReviews();
            }
        }
        return null;
	}

	public Review findById(User user,long id) {
		return user.getReviews().get((int)id);
	}

	public void save(User user,Review review) {

		long id = nextId.getAndIncrement();

		review.setId(id);

		int index = users.indexOf(user);
        users.get(index).getReviews().add((int)id, review);
	}

    public void save(User user) {

		long id = nextId.getAndIncrement();

		user.setId(id);
        users.add(user);
	}

	public void deleteById(String author, long id) {
        for(User us: users){
            if(us.getUsername().equals(author)){
		        us.getReviews().remove((int)id);
            }
        }
	}
    
}
