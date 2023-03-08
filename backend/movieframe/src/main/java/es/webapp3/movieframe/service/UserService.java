package es.webapp3.movieframe.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.repository.UserRepository;
import jakarta.annotation.PostConstruct;

@Service
public class UserService {

    @Autowired
	private UserRepository usersRepository;

	@PostConstruct
	public void init() {
		
		usersRepository.save(new User("edwardKennedy","edu123456","Edward","edward@kennedy.com"));
        usersRepository.save(new User("hughjackman","maninthemiddle","Hugh","hugh@jack.com"));

		for(int i=0; i<10; i++) {
			usersRepository.save(new User("Username"+i, "Password"+i, "Name"+i, "Mail"+i));
		}
    }

    private AtomicLong nextId = new AtomicLong(1);

    public List<User> findUsers() {
        return usersRepository.findAll();
	}

	public Optional<User> findUser(Long id) {
        return usersRepository.findById(id);
	}

	public void saveUser(User user) {

		long id = nextId.getAndIncrement();

		user.setId(id);
        usersRepository.save(user);
	}
    
}
