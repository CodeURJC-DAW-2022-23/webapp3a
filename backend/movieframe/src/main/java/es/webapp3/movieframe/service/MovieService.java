package es.webapp3.movieframe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.webapp3.movieframe.model.Director;
import es.webapp3.movieframe.model.Movie;
import es.webapp3.movieframe.model.Review;
import es.webapp3.movieframe.repository.MovieRepository;

import java.sql.Blob;
import java.util.List;



@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Optional<Movie> findById(long id) {
		return movieRepository.findById(id);
	}
	
	/*public boolean exist(long id) {
		return movieRepository.existsById(id);
	}*/

	public Page<Movie> findAll(Pageable page) {
		return movieRepository.findAll(page);
	}
	
	public Movie findSingleByTitle(String title) {
		return movieRepository.findSingleByTitle(title);
	}

	public Page<Movie> findByTitle(String title,Pageable page) {
		return movieRepository.findByTitle(title,PageRequest.of(0,10));
	}
	public List<Movie> findByCategory(String category){
		return movieRepository.findByCategory(category);
	}
	
	public Movie save(Movie movie) {
		if(!movie.getTitle().equals("") && !movie.getCategory().equals("") && movie.getImageFile() != null){//mandatory fields --title, category, picture--
			movieRepository.save(movie);
			return movieRepository.findById(movie.getId()).orElseThrow();
		} else {
			return null;
		}
	}

	public void saveAPI(Movie movie) {
		movieRepository.save(movie);
	}

	public void update(Long id, Movie newMovie){

		Optional<Movie> movie = movieRepository.findById(id);
		
		int votes = movie.get().getVotes();//votes do not change
		newMovie.setVotes(votes);
		List<Director> directors = movie.get().getDirectors();//directors do not change
		newMovie.setDirectors(directors);
		List<Review> reviews = movie.get().getReviews();//reviews list does not change
		for(Review oldReview: reviews){
			newMovie.setReview(oldReview);
		}
		Blob image = movie.get().getImageFile();//picture does not change
		newMovie.setImageFile(image);

		if(newMovie.getTitle().equals("")){
			newMovie.setTitle(movie.get().getTitle());
		}
		if(newMovie.getCategory().equals("")){
			newMovie.setCategory(movie.get().getCategory());
		}
		if(newMovie.getDescription().equals("")){
			newMovie.setDescription(movie.get().getDescription());
		}

		newMovie.setId(id);
		movieRepository.save(newMovie);
	}

	public void delete(long id) {
		movieRepository.deleteById(id);
	}
}
