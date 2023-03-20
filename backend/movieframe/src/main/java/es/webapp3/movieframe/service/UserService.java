package es.webapp3.movieframe.service;

import java.util.List;
import java.util.Optional;
//import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository usersRepository;

    //private AtomicLong nextId = new AtomicLong(1);

	public Page<User> findAll(Pageable page) {
        return usersRepository.findAll(page);
	}

	public Optional<User> findById(Long id) {
        return usersRepository.findById(id);
	}

	public Optional<User> findByName(String username) {
        return usersRepository.findByName(username);
	}

	public void save(User user) {
        usersRepository.save(user);
	}
    
}
