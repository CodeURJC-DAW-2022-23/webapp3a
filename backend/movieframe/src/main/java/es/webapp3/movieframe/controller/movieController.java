package es.webapp3.movieframe.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.webapp3.movieframe.model.Movie;
import es.webapp3.movieframe.service.MovieService;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/movies")
public class movieController {
    
    @Autowired
    private MovieService movieService;

    @PostConstruct
    public void init(){

        Movie movie1 = new Movie("Avatar", "Sci-Fi", "Jake Sully vive con su nueva familia en el planeta de Pandora. Cuando una amenaza conocida regresa, Jake debe trabajar con Neytiri y el ejército de la raza na'vi para proteger su planeta.","/sample_images/uploads/film1.jpg",3,"https://www.youtube.com/embed/FSyWAxUg3Go");  
        movieService.save(movie1);
        Movie movie2 = new Movie("Ant-Man and the Wasp: Quantumania", "Adventure", "Ant-Man and the Wasp: Quantumania. Lang y van Dyne exploran el Reino Cuántico junto con su familia y se enfrentan a Kang el Conquistador.","/sample_images/uploads/film2.jpg",4,null);   
        movieService.save(movie2);
        Movie movie3 = new Movie("Missing", "mystery", "June Allen, una adolescente que intenta encontrar a su madre desaparecida luego de que esta desaparece de vacaciones en Colombia con su nuevo novio.","/sample_images/uploads/film3.jpg",2,null);   
        movieService.save(movie3);
        Movie movie4 = new Movie("El Gato con Botas: el último deseo", "Animation", "La película es una secuela de El Gato con Botas y es derivada de la franquicia de Shrek.","/sample_images/uploads/film4.jpg",1,null);   
        movieService.save(movie4);
        Movie movie5 = new Movie("As bestas", "Drama", "Está ambientada en Galicia y rodada en francés, español y gallego.","/sample_images/uploads/film5.jpg",3,null);     
        movieService.save(movie5);
        Movie movie6 = new Movie("Los Fabelman", "Drama", "Los Fabelman es una película de drama y crecimiento estadounidense de 2022 dirigida y coproducida por Steven Spielberg a partir de un guion que escribió junto con Tony Kushner. Es una semi-autobiografía vagamente basada en los primeros años de vida de Spielberg, contada a través de una historia original del ficticio Sammy Fabelman, un joven aspirante a cineasta. La película está protagonizada por Gabriel LaBelle como Sammy, con Michelle Williams, Paul Dano, Seth Rogen y Judd Hirsch en papeles secundarios. La película está dedicada a los recuerdos de los padres de la vida real de Spielberg, Arnold Spielberg y Leah Adler.","/sample_images/uploads/film6.jpg",5,null);      
        movieService.save(movie6);
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("movieframe", movieService.findAll());
        return "initial_screen.html";
    }

    @GetMapping("/{id}")
    public String getMovie(Model model,@PathVariable Long id){
//obtener de la bd la peli seleccionada, añadirlo al modelo y devolver la pantalla
        Optional<Movie> movie = movieService.findById(id);

        if(movie.isPresent()){
            model.addAttribute("movie",movie);
            return "movie_screen.html";
        }else{
            return "404.html";
        }

        
    }
}
