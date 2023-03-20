package es.webapp3.movieframe.service;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.webapp3.movieframe.model.Director;
import es.webapp3.movieframe.model.Movie;
import es.webapp3.movieframe.model.Review;
import es.webapp3.movieframe.model.User;
import es.webapp3.movieframe.repository.DirectorRepository;
import es.webapp3.movieframe.repository.MovieRepository;
import es.webapp3.movieframe.repository.ReviewRepository;
import es.webapp3.movieframe.repository.UserRepository;

@Service
public class DataBaseInitializer {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
	private UserRepository usersRepository;

    @Autowired
	private DirectorRepository directorRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${security.user}")
	private String user;

    @Value("${security.encodedPassword}")
	private String encodedPassword;

	@PostConstruct
    public void init() throws IOException, URISyntaxException{

        Review review1 = new Review(/*"edwardkennedy",*/3,"Africa's burgeoning talents from across the continent ...");
      
        Review review2 = new Review(/*"edwardkennedy",*/5,"Magnolia Pictures has acquired U.S. the...");

        Review review3 = new Review(/*"hughjackman",*/4,"New Line’s remake of “Going in Style” launched with a moderate $...");

        Director director1 = new Director("Joss Whedon","Joseph Hill Whedon","23 de junio de 1964 (58 años), New York","New York",4.0,"Action", "Sci-Fi", "Adventure");
        setDirectorImage(director1,"/images/uploads/josh_whedon3.jpg");

        director1.setBiography("Joseph Hill Whedon (/ˈhwiːdən/; born June 23, 1964) is an American filmmaker, composer, and comic book...");

        Movie movie1 = new Movie();
        movie1.setTitle("Avatar");
        movie1.setCategory("Sci-Fi");
        movie1.setDescription("Jake Sully vive con su nueva familia en el planeta de Pandora. \nCuando una amenaza conocida regresa, Jake debe trabajar con Neytiri \ny el ejército de la raza na'vi para proteger su planeta.");
        movie1.setVotes(3);  
		setMovieImage(movie1, "/images/uploads/film1.jpg");

		movieRepository.save(movie1);

        
		
        Movie movie2 = new Movie();
        movie2.setTitle("Ant-Man and the Wasp: Quantumania");
        movie2.setCategory("Adventure");
        movie2.setDescription("Ant-Man and the Wasp: Quantumania. Lang y van Dyne exploran el Reino Cuántico junto con su familia y se enfrentan a Kang el Conquistador.");
        movie2.setVotes(4);   
		setMovieImage(movie2,"/images/uploads/film2.jpg");

        movieRepository.save(movie2);

        Movie movie3 = new Movie();
        movie3.setTitle("Missing");
        movie3.setCategory("mystery");
        movie3.setDescription("June Allen, una adolescente que intenta encontrar a su madre desaparecida luego de que esta desaparece de vacaciones en Colombia con su nuevo novio.");
        movie3.setVotes(2);   
		setMovieImage(movie3,"/images/uploads/film3.jpg"); 

        movieRepository.save(movie3);

        Movie movie4 = new Movie();
        movie4.setTitle("El Gato con Botas: el último deseo");
        movie4.setCategory("Animation");
        movie4.setDescription("La película es una secuela de El Gato con Botas y es derivada de la franquicia de Shrek.");
        movie4.setVotes(1);   
		setMovieImage(movie4,"/images/uploads/film4.jpg");

        movieRepository.save(movie4);   

        Movie movie5 = new Movie();
        movie5.setTitle("As bestas");
        movie5.setCategory("Drama");
        movie5.setDescription("Está ambientada en Galicia y rodada en francés, español y gallego.");
        movie5.setVotes(3);     
		setMovieImage(movie5,"/images/uploads/film5.jpg");

        movieRepository.save(movie5);
        //director1 directed movie5
        //movie5 was directed by director1
        director1.getMovies().add(movie5);

        directorRepository.save(director1);
        
        Movie movie6 = new Movie();
        movie6.setTitle("Los Fabelman");
        movie6.setCategory("Drama");
        movie6.setDescription("Contada a través de una historia original del ficticio Sammy Fabelman, un joven aspirante a cineasta. La película está dedicada a los recuerdos de los padres de la vida real de Spielberg, Arnold Spielberg y Leah Adler.");
        movie6.setVotes(5); 
        setMovieImage(movie6,"/images/uploads/film6.jpg");

        movieRepository.save(movie6);  		
		
		User user1 = new User("edwardKennedy",passwordEncoder.encode("edu123456"),"Edward","mimiteemoc999@gmail.com","USER");     
        usersRepository.save(user1);

        User user2 = new User(user,"{bcrypt}"+encodedPassword,"Hugh","hugh@jack.com","USER","ADMIN");        
        usersRepository.save(user2);
        
        //a single movie can have many reviews
        review1.setMovie(movie1);
        review2.setMovie(movie1);

        review3.setMovie(movie3);

        //a single user can also have several reviews
        review1.setUser(user1);
        review2.setUser(user1);

        reviewRepository.save(review1);
        reviewRepository.save(review2);
        reviewRepository.save(review3);
    }

    public void setMovieImage(Movie movie, String ClasspathResource)throws IOException{
		Resource image = new ClassPathResource(ClasspathResource);
		movie.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}

    public void setDirectorImage(Director director, String ClasspathResource)throws IOException{
		Resource image = new ClassPathResource(ClasspathResource);
		director.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}
   
}
