package com.aluracursos.screenmatch.dto.response;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int codeStatus;
    private String typeStatus;
    private String message;
    private String path;
    private LocalDateTime timestamp;
    private Map<String, List<String>> errors;
}
