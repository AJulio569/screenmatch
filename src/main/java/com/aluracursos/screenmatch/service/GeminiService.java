package com.aluracursos.screenmatch.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class GeminiService {
    @Value("${gemini.api.key}")
    private String apiKey;

    public  String oftenerTransduction(String text) {
        String model = "gemini-2.0-flash-lite";
        String prompt = "Traduce el siguiente texto al español: " + text;

        Client client = new Client.Builder().apiKey(apiKey).build();

        try {
            GenerateContentResponse prestates = client.models.generateContent(
                    model,
                    prompt,
                    null
            );

            if (!Objects.requireNonNull(prestates.text()).isEmpty()) {
                return prestates.text();
            }
        } catch (Exception e) {
            System.err.println("Error al llamar a la API de Gemini para traducción: " + e.getMessage());
        }

        return null;
    }
}
