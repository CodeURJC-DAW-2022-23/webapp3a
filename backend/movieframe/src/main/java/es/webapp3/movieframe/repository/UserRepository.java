package es.webapp3.movieframe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.webapp3.movieframe.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

    List<User> findAllUsers();
    
    User findReviewByAuthor(String name);

    void saveUser(User newUser);

    @Query(value = "SELECT u.name, u.lastname, u.email, r.description FROM User u JOIN Review r ON r.name = u.name WHERE u.name = :name", 
            nativeQuery = true)
    User findReviewByAuthorQuery(Long id);
}
 