package es.webapp3.movieframe.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import es.webapp3.movieframe.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

    /*@Query(value = "SELECT u.name, u.lastname, u.email, r.description FROM User u JOIN Review r ON r.name = u.name WHERE u.name = :name", 
            nativeQuery = true)
    User findReviewByAuthorQuery(Long id);*/
    Optional<User> findByName(String name);

    Page<User> findAll(Pageable page);
}
 