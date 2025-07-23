package com.aluracursos.screenmatch.controller;

import com.aluracursos.screenmatch.dto.response.MovieResponse;
import com.aluracursos.screenmatch.service.ApiService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@Validated
public class MovieController {
    private  final ApiService service;

    public MovieController(ApiService service) {
        this.service = service;
    }

    @GetMapping("/v1/{title}")
    public ResponseEntity<MovieResponse> getMovie(
            @PathVariable @NotBlank(message = "The title cannot be empty") String title) {
        return ResponseEntity.ok(service.getMovieResponse(title));
    }

    @PostMapping("/v1/save/{title}")
    public ResponseEntity<MovieResponse> saveMovie(
            @PathVariable @NotBlank(message = "The title cannot be empty") String title) {
        MovieResponse response = service.getMovieResponseAndSave(title);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/v1/all")
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.ok(service.findAllMovies());
    }

    @GetMapping("/v1/search")
    public ResponseEntity<List<MovieResponse>> searchByTitle(
            @RequestParam @NotBlank(message = "Title query cannot be empty") String title) {
        return ResponseEntity.ok(service.findByTitleContaining(title));
    }
}
