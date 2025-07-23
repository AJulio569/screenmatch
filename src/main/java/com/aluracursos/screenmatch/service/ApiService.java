package com.aluracursos.screenmatch.service;

import com.aluracursos.screenmatch.dto.response.MovieResponse;
import com.aluracursos.screenmatch.entity.MovieEntity;
import com.aluracursos.screenmatch.exception.MovieNotFoundException;
import com.aluracursos.screenmatch.mapper.IMovieMapper;
import com.aluracursos.screenmatch.repository.IMovieRepository;
import com.aluracursos.screenmatch.util.ConvertJacksonData;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ApiService {
    private final IMovieMapper mapper;
    private final ConvertJacksonData jacksonData;
    private final GeminiService geminiService;
    private final IMovieRepository movieRepository;

    public ApiService(IMovieMapper mapper, ConvertJacksonData jacksonData, GeminiService geminiService, IMovieRepository movieRepository) {
        this.mapper = mapper;
        this.jacksonData = jacksonData;
        this.geminiService = geminiService;
        this.movieRepository = movieRepository;
    }

    public MovieResponse getMovieResponse(String title) {
        MovieEntity entity = getMovie(title);
        String translatedPlot = geminiService.oftenerTransduction(entity.getPlot());
        entity.setPlot(translatedPlot);

        if (entity.getRatings() != null) {
            entity.getRatings().forEach(r -> r.setMovie(entity));
        }
        return mapper.toResponse(entity);
    }

    public MovieEntity getMovie(String title) {
        try {
            URI uri = URI.create("http://www.omdbapi.com/?t=" + URLEncoder.encode(title, StandardCharsets.UTF_8) + "&apikey=f46e6e69");
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            MovieEntity movie = jacksonData.fromJson(response.body(), MovieEntity.class);

            if (!"True".equalsIgnoreCase(movie.getResponse())) {
                throw new MovieNotFoundException("Movie not found or invalid response: " + title);
            }

            if (movie.getRatings() != null) {
                movie.getRatings().forEach(rating -> rating.setMovie(movie));
            }
            return movie;

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error consuming the API", e);
        }
    }

    public MovieResponse getMovieResponseAndSave(String title) {
        MovieEntity entity = getMovie(title);
        entity.setPlot(geminiService.oftenerTransduction(entity.getPlot()));

        if (entity.getRatings() != null) {
            entity.getRatings().forEach(r -> r.setMovie(entity));
        }

        MovieEntity saved = movieRepository.save(entity);
        return mapper.toResponse(saved);
    }

    public List<MovieResponse> findAllMovies() {
        List<MovieEntity> movies = movieRepository.findAll();
        return mapper.toResponseList(movies);
    }

    public List<MovieResponse> findByTitleContaining(String title) {
        List<MovieEntity> matches = movieRepository.findByTitleContainingIgnoreCase(title);
        return mapper.toResponseList(matches);
    }
}
