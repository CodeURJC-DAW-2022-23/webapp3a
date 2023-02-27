package es.webapp3.movieframe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.webapp3.movieframe.model.movie;
import es.webapp3.movieframe.repository.MovieRepository;
import jakarta.annotation.PostConstruct;

@Service
public class DataBaseInizialiter {

    @Autowired 
    private MovieRepository movieRepository;

    @PostConstruct
    public void init(){
        
        //sample movies
        movie movie1 = new es.webapp3.movieframe.model.movie("Avatar", "James Cameron", "Sci-Fi", "Jake Sully vive con su nueva familia en el planeta de Pandora. Cuando una amenaza conocida regresa, Jake debe trabajar con Neytiri y el ejército de la raza na'vi para proteger su planeta.");   
        movieRepository.save(movie1);
        movie movie2 = new es.webapp3.movieframe.model.movie("Ant-Man and the Wasp: Quantumania", "Peyton Reed", "Sci-Fi", "Ant-Man and the Wasp: Quantumania es una película de superhéroes estadounidense basada en los personajes de Marvel Comics, Scott Lang / Ant-Man y Hope van Dyne / Wasp. Producida por Marvel Studios y distribuida por Walt Disney Studios Motion Pictures, es la secuela de Ant-Man y Ant-Man and the Wasp y la película número 31 del Universo cinematográfico de Marvel, también es la primera película de la Fase 5. La película está dirigida por Peyton Reed a partir de un guion de Jeff Loveness, y está protagonizada por Paul Rudd como Scott Lang y Evangeline Lilly como Hope van Dyne, junto a Jonathan Majors, Kathryn Newton, Michelle Pfeiffer, Michael Douglas, David Dastmalchian, William Jackson Harper y Katy O'Brian. En la película, Lang y van Dyne exploran el Reino Cuántico junto con su familia y se enfrentan a Kang el Conquistador.");   
        movieRepository.save(movie2);
        movie movie3 = new es.webapp3.movieframe.model.movie("Missing", "Nick Johnson · Will Merrick", "Sci-Fi", "Missing es una película estadounidense de misterio y suspenso de 2023 escrita y dirigida por Nick Johnson y Will Merrick, a partir de una historia de Sev Ohanian y Aneesh Chaganty. Es una secuela independiente de la película Searching de 2018. La película está protagonizada por Storm Reid, Joaquim de Almeida, Ken Leung, Amy Landecker, Daniel Henney y Nia Long. Su trama sigue a June Allen, una adolescente que intenta encontrar a su madre desaparecida luego de que esta desaparece de vacaciones en Colombia con su nuevo novio.");   
        movieRepository.save(movie3);
        movie movie4 = new es.webapp3.movieframe.model.movie("El Gato con Botas: el último deseo", "Joel Crawford", "Sci-Fi", "El Gato con Botas: El último deseo es una película estadounidense de comedia de aventuras animada por computadora producida por DreamWorks Animation y distribuida por Universal Pictures. La película es una secuela de El Gato con Botas y es derivada de la franquicia de Shrek. Está dirigida por Joel Crawford, con la codirección de Januel Mercado, y está protagonizada por las voces de Antonio Banderas y Salma Hayek, que retoman sus papeles del Gato con Botas y Kitty Patitas Suaves, respectivamente.");   
        movieRepository.save(movie4);
        movie movie5 = new es.webapp3.movieframe.model.movie("As bestas", "Rodrigo Sorogoyen", "Sci-Fi", "As bestas es una película de thriller rural hispanofrancés de 2022 dirigida por Rodrigo Sorogoyen y protagonizada por Denis Ménochet, Marina Foïs, Luis Zahera y Diego Anido. Está ambientada en Galicia y rodada en francés, español y gallego.");   
        movieRepository.save(movie5);
        movie movie6 = new es.webapp3.movieframe.model.movie("Los Fabelman", "Steven Spielberg", "Sci-Fi", "Los Fabelman es una película de drama y crecimiento estadounidense de 2022 dirigida y coproducida por Steven Spielberg a partir de un guion que escribió junto con Tony Kushner. Es una semi-autobiografía vagamente basada en los primeros años de vida de Spielberg, contada a través de una historia original del ficticio Sammy Fabelman, un joven aspirante a cineasta. La película está protagonizada por Gabriel LaBelle como Sammy, con Michelle Williams, Paul Dano, Seth Rogen y Judd Hirsch en papeles secundarios. La película está dedicada a los recuerdos de los padres de la vida real de Spielberg, Arnold Spielberg y Leah Adler.");   
        movieRepository.save(movie6);
    }
    


}
