package es.webapp3.movieframe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.webapp3.movieframe.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

    List<User> findAllUsers();
    
    User findReviewByAuthor(String name);

    void saveUser(User newUser);

    
    
}
