package com.aluracursos.screenmatch.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.Objects;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    public  String obtenerTraduccion(String text) {
        String model = "gemini-2.0-flash-lite";
        String prompt = "Traduce el siguiente texto al español: " + text;

        Client cliente = new Client.Builder().apiKey(apiKey).build();

        try {
            GenerateContentResponse respuesta = cliente.models.generateContent(
                    model,
                    prompt,
                    null // Parámetro para configuraciones adicionales
            );

            if (!Objects.requireNonNull(respuesta.text()).isEmpty()) {
                return respuesta.text();
            }
        } catch (Exception e) {
            System.err.println("Error al llamar a la API de Gemini para traducción: " + e.getMessage());
        }

        return null;
    }


}
