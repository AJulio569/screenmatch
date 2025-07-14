package com.aluracursos.screenmatch.service;

import com.aluracursos.screenmatch.dto.response.MovieResponse;
import com.aluracursos.screenmatch.entity.MovieEntity;
import com.aluracursos.screenmatch.exception.MovieNotFoundException;
import com.aluracursos.screenmatch.mapper.IMovieMapper;
import com.aluracursos.screenmatch.util.ConvertJacksonData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class ApiService {
    private final IMovieMapper mapper;
    private final ConvertJacksonData jacksonData;
    private final GeminiService geminiService;

    public ApiService(IMovieMapper mapper, ConvertJacksonData jacksonData, GeminiService geminiService) {
        this.mapper = mapper;
        this.jacksonData = jacksonData;

        this.geminiService = geminiService;
    }

    public MovieResponse getMovieResponse(String title) {
        MovieEntity entity = getMovie(title);
        String translatedPlot = geminiService.obtenerTraduccion(entity.getPlot());
        entity.setPlot(translatedPlot);
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
            return movie;

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error consuming the API", e);
        }
    }
}
