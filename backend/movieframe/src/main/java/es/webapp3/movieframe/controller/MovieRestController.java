package es.webapp3.movieframe.controller;

import java.io.IOException;
import java.net.URI;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.webapp3.movieframe.model.Director;
import es.webapp3.movieframe.model.Movie;
import es.webapp3.movieframe.model.Review;
import es.webapp3.movieframe.service.DirectorService;
import es.webapp3.movieframe.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class MovieRestController {
    
    @Autowired
    private MovieService movieService;

    @Autowired
    private DirectorService directorService;

    @Operation(summary = "Get movies")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found movies list", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))
        }),
        @ApiResponse(responseCode = "404", description = "No movie founded", content = @Content)
    })
    @GetMapping("/api/movies")
    public Page<Movie> getMoviesAPI(Model model,Pageable page){

        return movieService.findAll(page);
    }

    @GetMapping("/movies")
    public Page<Movie> getMovies(Model model,Pageable page){

        return movieService.findAll(page);
    }

    @Operation(summary = "Get movies by a name introduced in a dialog box")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found movies", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))
        }),
        @ApiResponse(responseCode = "404", description = "No movie matches this name", content = @Content)
    })
    @GetMapping("/api/movies/name")
    public Page<Movie> searchMovieAPI(@RequestBody Movie movie,Pageable page){      

        return movieService.findByTitle(movie.getTitle(),page);      
    }

    @GetMapping("/movies/name")
    public Page<Movie> searchMovie(@RequestBody Movie movie,Pageable page){      

        return movieService.findByTitle(movie.getTitle(),page);      
    }

    @Operation(summary = "Get movie")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found movie", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No movie with this id was found", content = @Content)
    })
    @GetMapping("/api/movies/{id}")
    public ResponseEntity<Movie> getMovieAPI(@PathVariable Long id) {

        Optional<Movie> movie = movieService.findById(id);

        if(movie.isPresent()){
            return new ResponseEntity<>(movie.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }   
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieBack(@PathVariable Long id) {

        Optional<Movie> movie = movieService.findById(id);

        if(movie.isPresent()){
            return new ResponseEntity<>(movie.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }   
    }

    @Operation(summary = "Update movie")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated movie", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No movie with this id was found to update", content = @Content)
    })
    @PutMapping("/api/movies/{id}")
    public ResponseEntity<Movie> movieUpdating(@RequestBody Movie newMovie,@PathVariable Long id) {

        Optional<Movie> movie = movieService.findById(id);

        if (movie.isPresent()) {

            newMovie.setVotes(movie.get().getVotes());//votes do not change

            newMovie.setDirectors(movie.get().getDirectors());//directors do not change

            for(var i=0; i<movie.get().getReviews().size(); i++){//reviews list does not change
                newMovie.setReview(movie.get().getReviews().get(i));
            }
            
            newMovie.setImageFile(movie.get().getImageFile());//picture does not change

            if(newMovie.getTitle().isEmpty()){
                newMovie.setTitle(movie.get().getTitle());
            }
            if(newMovie.getCategory().isEmpty()){
                newMovie.setCategory(movie.get().getCategory());
            }
            if(newMovie.getDescription().isEmpty()){
                newMovie.setDescription(movie.get().getDescription());
            }

            newMovie.setId(movie.get().getId());

            return new ResponseEntity<>(movieService.update(id,newMovie),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Post movie")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Posted movie", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))
        }),
        @ApiResponse(responseCode = "404", description = "No movie posted", content = @Content)
    })
    @PostMapping("/api/movies")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Movie> newMovie(@RequestBody Movie movie) {

        URI location = fromCurrentRequest().path("/{id}")
            .buildAndExpand(movie.getId()).toUri();

        movieService.saveAPI(movie);

        return ResponseEntity.created(location).body(movie); 
    }

    @Operation(summary = "Post movie image")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Posted movie image", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No movie with this id was found to post it an image", content = @Content)
    })
    @PostMapping("/api/movies/{id}/image")
    public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException {

        Optional<Movie> movie = movieService.findById(id);
     
        URI location = fromCurrentRequest().build().toUri();

        movie.get().setImage(true);

        movie.get().setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));

        movieService.saveAPI(movie.get());

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Get movie image")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found movie image", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No movie image with this id was found", content = @Content)
    })
    @GetMapping("/api/movies/{id}/image")
    public ResponseEntity<Object> downloadImageAPI(@PathVariable long id) throws SQLException {
        
        Optional<Movie> movie = movieService.findById(id);
        
        if (movie.isPresent() && movie.get().getImageFile() != null) {
            Resource file = new InputStreamResource(movie.get().getImageFile().getBinaryStream());
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .contentLength(movie.get().getImageFile().length())
                .body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    } 

    @GetMapping("/movies/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
        
        Optional<Movie> movie = movieService.findById(id);
        
        if (movie.isPresent() && movie.get().getImageFile() != null) {
            Resource file = new InputStreamResource(movie.get().getImageFile().getBinaryStream());
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .contentLength(movie.get().getImageFile().length())
                .body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    } 

    @Operation(summary = "Get movie director image")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found movie image", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No movie image with this id was found", content = @Content)
    })
    @GetMapping("/api/movies/{id}/director/image")
    public ResponseEntity<Object> downloadDirectorImageAPI(@PathVariable long id) throws SQLException {
        
        Optional<Director> director = directorService.findById(id);
        
        if (director.isPresent() && director.get().getImageFile() != null) {
            Resource file = new InputStreamResource(director.get().getImageFile().getBinaryStream());
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .contentLength(director.get().getImageFile().length())
                .body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/movies/{id}/director/image")
    public ResponseEntity<Object> downloadDirectorImage(@PathVariable long id) throws SQLException {
        
        Optional<Director> director = directorService.findById(id);
        
        if (director.isPresent() && director.get().getImageFile() != null) {
            Resource file = new InputStreamResource(director.get().getImageFile().getBinaryStream());
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .contentLength(director.get().getImageFile().length())
                .body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}