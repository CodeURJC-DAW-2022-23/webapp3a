package es.webapp3.movieframe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.webapp3.movieframe.model.Director;

public interface DirectorRepository extends JpaRepository<Director,Long>{
    
}
