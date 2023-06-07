package es.webapp3.movieframe.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.webapp3.movieframe.model.Director;
import es.webapp3.movieframe.service.DirectorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class DirectorRestController {

    @Autowired
    private DirectorService directorService;

    @Operation(summary = "Get director")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found director", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Director.class))
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "No director with this id was found", content = @Content)
    })
    @GetMapping("/api/directors/{id}")
    public ResponseEntity<Director> getDirector(@PathVariable long id){

        Optional<Director> director = directorService.findById(id);

        if(director.isPresent()){
            return new ResponseEntity<>(director.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
