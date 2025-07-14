package com.aluracursos.screenmatch.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingsEntity {
    @JsonAlias("Source")
    private String source;

    @JsonAlias("Value")
    private String value;

    @Override
    public String toString() {
        return "{\"Source\":\"" + source + "\", \"Value\":\"" + value + "\"}";
    }
}
