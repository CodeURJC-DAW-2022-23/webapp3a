package es.webapp3.movieframe.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/* 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
*/
import es.webapp3.movieframe.model.Movie;
import es.webapp3.movieframe.repository.MovieRepository;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Optional<Movie> findById(long id) {
		return movieRepository.findById(id);
	}
	
	public boolean exist(long id) {
		return movieRepository.existsById(id);
	}

	public List<Movie> findAll() {
		return movieRepository.findAll();
	}
	
	public List<Movie> findSingleByTitle(String title) {
		return movieRepository.findByTitle(title);
	}
	public List<Movie> findByGender(String gender){
		return movieRepository.findByGender(gender);
	}
	
	public void save(Movie movie) {
		movieRepository.save(movie);
	}

	public void delete(long id) {
		movieRepository.deleteById(id);
	}
}
