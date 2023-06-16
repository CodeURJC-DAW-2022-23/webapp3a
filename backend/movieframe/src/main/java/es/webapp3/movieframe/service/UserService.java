package es.webapp3.movieframe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository usersRepository;

    @Autowired
	private PasswordEncoder passwordEncoder;

	public Page<User> findAll(Pageable page) {
        return usersRepository.findAll(page);
	}

	public Optional<User> findById(Long id) {
        return usersRepository.findById(id);
	}

	public Optional<User> findByUsername(String username) {
        return usersRepository.findByUsername(username);
	}

	public User save(User user) {
		user.setRoles("USER");
		if(!user.getUsername().equals("") && !user.getName().equals("") && !user.getEmail().equals("") && !user.getEncodedPassword().equals("") && user.getImageFile() != null){
			user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));
			usersRepository.save(user);
			return usersRepository.findById(user.getId()).orElseThrow();
		} else {
			return null;
		}
	}

	public User saveAPI(User user) {
		user.setRoles("USER");
		if(!user.getUsername().equals("") && !user.getName().equals("") && !user.getEmail().equals("") && !user.getEncodedPassword().equals("")){
			user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));
			usersRepository.save(user);
			return usersRepository.findById(user.getId()).orElseThrow();

		} else {
			return null;
		}
	}

	public void saveUpdateAPI(User user) {
		usersRepository.save(user);
	}

	public User update(String userName, User newUser) {
		usersRepository.save(newUser);
		return usersRepository.findById(newUser.getId()).orElseThrow();
	}
    
}
