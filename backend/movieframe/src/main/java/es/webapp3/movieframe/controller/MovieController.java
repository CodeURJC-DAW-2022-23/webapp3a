package es.webapp3.movieframe.controller;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.webapp3.movieframe.model.movie;
import es.webapp3.movieframe.service.MovieService;

@Controller
public class MovieController {

    @Autowired 
    private MovieService movieService;
    
    @GetMapping("/movie/{title}")
	public String showBook(Model model, @PathVariable String title) {

		movie movie = movieService.findSingleByTitle(title);
		model.addAttribute("movie",movie);
		return "movie_screen";

	}
	@GetMapping("/movies/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

		Optional<movie> movie = movieService.findById(id);
		if (movie.isPresent() && movie.get().getImageFile() != null) {

			Resource file = new InputStreamResource(movie.get().getImageFile().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpg")
					.contentLength(movie.get().getImageFile().length()).body(file);

		} else {
			return ResponseEntity.notFound().build();
		}
	}


    
}
