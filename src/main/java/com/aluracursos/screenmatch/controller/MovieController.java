package com.aluracursos.screenmatch.controller;

import com.aluracursos.screenmatch.dto.response.MovieResponse;
import com.aluracursos.screenmatch.service.ApiService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
