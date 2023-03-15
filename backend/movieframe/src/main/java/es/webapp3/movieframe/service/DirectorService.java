package es.webapp3.movieframe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.webapp3.movieframe.model.Director;
import es.webapp3.movieframe.repository.DirectorRepository;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    public void save(Director director) {
		directorRepository.save(director);
	}

    public Optional<Director> findById(Long id) {
		Optional<Director> director = directorRepository.findById(id);
        return director;
	}
    
}
