package es.webapp3.movieframe.service;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
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

	@PostConstruct
    public void init() throws IOException, URISyntaxException{

        Review review1 = new Review(/*"edwardkennedy",*/3,"Africa's burgeoning talents from across the continent ...");
      
        Review review2 = new Review(/*"edwardkennedy",*/5,"Magnolia Pictures has acquired U.S. the...");

        Review review4 = new Review(/*"edwardkennedy",*/4," La escena que mas me gustó de toda la película es cuando Cooper abandona a su hija Murph cuando parte a cumplir la misión y cuando Cooper y Amelia Brand viajan al mas allá en el espacio");
     
        Review review3 = new Review(/*"hughjackman",*/4,"New Line’s remake of “Going in Style” launched with a moderate $...");

        Director director1 = new Director("Joss Whedon","Joseph Hill Whedon","23 de junio de 1964 (58 años), New York","New York",4.0,"Action");
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
		setMovieImage(movie3,"/images/uploads/mv-item3.jpg"); 

        movieRepository.save(movie3);

        Movie movie4 = new Movie();
        movie4.setTitle("3069");
        movie4.setCategory("Drama");
        movie4.setDescription("En la alemania oriental, una niña de 10 años es secuestrada y retenida en una habitación durante 8 años hasta que esta consigue escapar. Retrato biográfico de Natasha Kamush");
        movie4.setVotes(4);   
		setMovieImage(movie4,"/images/uploads/mv-it5.jpg");

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

        Movie movie9 = new Movie();
        movie9.setTitle("Jurassic park");
        movie9.setCategory("Adventure");
        movie9.setDescription("Trama basada en el libro homónimo de Michael Crichton que relata las vivencias de un grupo de personas en un parque de diversiones con dinosaurios clonados, creado por un filántropo multimillonario y un equipo de cinetíficos genetistas");
        movie9.setVotes(2); 
        setMovieImage(movie9,"/images/uploads/mv-it9.jpg");

        movieRepository.save(movie9);

        Movie movie10 = new Movie();
        movie10.setTitle("Harry poter");
        movie10.setCategory("fiction science");
        movie10.setDescription("Tras la evidencias de que Voldermort ha regresado, Harry se verá envuelto en el tradicional torneo de los 3 magos. con la ayuda de Ron y Hermione, se prepará para competir en una nueva lucha que él no ha elegido.");
        movie10.setVotes(4); 
        setMovieImage(movie10,"/images/uploads/mv-it10.jpg");

        movieRepository.save(movie10);

        Movie movie11 = new Movie();
        movie11.setTitle("Guardinaes de la galaxia");
        movie11.setCategory("fiction science");
        movie11.setDescription("Peter Quill, todavía conmocionado por la pérdida de Gamora, debe reunir al equipo en torno a él para defender el universo mientras protege a uno de los suyos. Una misión que no siempre ha acabado con éxito y podría suponer el fin de los Guardianes. ");
        movie11.setVotes(5); 
        setMovieImage(movie11,"/images/uploads/mv-it11.jpg");

        movieRepository.save(movie11);  	
        
        Movie movie12 = new Movie();
        movie12.setTitle("Oblivion");
        movie12.setCategory("Mystery");
        movie12.setDescription("Dos semanas antes de que decida marcharse por siempre de la tierra, Harper rescata a una mujer de una nave espacial, provocando una batalla final para salvar a  la humanidad");
        movie12.setVotes(5); 
        setMovieImage(movie12,"/images/uploads/mv-it1.jpg");

        movieRepository.save(movie12);

        Movie movie17 = new Movie();
        movie17.setTitle("interstellar");
        movie17.setCategory("Nature");
        movie17.setDescription("Un grupo de científicos y exploradores se embarcan en un viaje espacial para encontrar un lugar con las condiciones necesarias para reemplazar a la tierra y comenzar una nueva vida allí");
        movie17.setVotes(3); 
        setMovieImage(movie17,"/images/uploads/mv-item1.jpg");

        movieRepository.save(movie17);

        Movie movie18 = new Movie();
        movie18.setTitle("the walk");
        movie18.setCategory("suspense");
        movie18.setDescription("un equilibrista francés que se gana la vida actuando en la calle se propone como reto recorrer sobre un cable el espacio quee separa las dos torres gemelas de NY que se encuentran en proceso de construcción");
        movie18.setVotes(5); 
        setMovieImage(movie18,"/images/uploads/mv-item4.jpg");

        movieRepository.save(movie18);

        Movie movie19 = new Movie();
        movie19.setTitle("into the wild");
        movie19.setCategory("suspense");
        movie19.setDescription("Christopher McCandless, un estudiante de 10, abandona todas susposesiones, dona sus ahorros a la caridad y hace autostop hasta llegar a Alaska para vivir en una zona selvática");
        movie19.setVotes(5); 
        setMovieImage(movie19,"/images/uploads/mv-item5.jpg");

        movieRepository.save(movie19); 
		
		User user1 = new User("edward",passwordEncoder.encode("edu123456"),"USER");
        user1.setName("Edward");
        user1.setEmail("mimiteemoc999@gmail.com");
        setUserImage(user1,"/images/uploads/userava1.jpg");

        usersRepository.save(user1);

        User user2 = new User("hughjackman",passwordEncoder.encode("567890"),"ADMIN");
        user2.setName("Hugh");
        user2.setEmail("hugh@jack.com");
        setUserImage(user2,"/images/uploads/userava2.jpg");

        usersRepository.save(user2);
        
        //a single movie can have many reviews
        review1.setMovie(movie1);
        review2.setMovie(movie1);
        review4.setMovie(movie17);

        review3.setMovie(movie3);

        //a single user can also have several reviews
        review1.setUser(user1);
        review2.setUser(user1);
        review4.setUser(user1);

        reviewRepository.save(review1);
        reviewRepository.save(review2);
        reviewRepository.save(review3);
        reviewRepository.save(review4);
    }

    public void setMovieImage(Movie movie, String ClasspathResource)throws IOException{
		Resource image = new ClassPathResource(ClasspathResource);
		movie.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}

    public void setDirectorImage(Director director, String ClasspathResource)throws IOException{
		Resource image = new ClassPathResource(ClasspathResource);
		director.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}
   
    public void setUserImage(User user, String ClasspathResource)throws IOException{
		Resource image = new ClassPathResource(ClasspathResource);
		user.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}
}
