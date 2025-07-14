package com.aluracursos.screenmatch.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieEntity {
    @JsonAlias("Title")
    private String title;

    @JsonAlias("Year")
    private String year;

    @JsonAlias("Rated")
    private String rated;

    @JsonAlias("Released")
    private String released;

    @JsonAlias("Runtime")
    private String runtime;

    @JsonAlias("Genre")
    private String genre;

    @JsonAlias("Director")
    private  String director;

    @JsonAlias("Writer")
    private String write;

    @JsonAlias("Actors")
    private String actors;

    @JsonAlias("Plot")
    private String plot;

    @JsonAlias("Language")
    private String language;

    @JsonAlias("Country")
    private String country;

    @JsonAlias("Awards")
    private String awards;

    @JsonAlias("Poster")
    private String poster;

    @JsonAlias("Ratings")
    private List<RatingsEntity> ratings;

    @JsonAlias("Metascore")
    private String metascore;

    private String imdbRating;

    private String imdbVotes;

    private String imdbID;

    @JsonAlias("Type")
    private String type;

    private String totalSeasons;

    @JsonAlias("Response")
    private String response;

    @Override
    public String toString() {
        return "{"
                + "\"title\":\"" + title + "\","
                + "\"year\":\"" + year + "\","
                + "\"rated\":\"" + rated + "\","
                + "\"released\":\"" + released + "\","
                + "\"runtime\":\"" + runtime + "\","
                + "\"genre\":\"" + genre + "\","
                + "\"director\":\"" + director + "\","
                + "\"writer\":\"" + write + "\","
                + "\"actors\":\"" + actors + "\","
                + "\"plot\":\"" + plot + "\","
                + "\"language\":\"" + language + "\","
                + "\"country\":\"" + country + "\","
                + "\"awards\":\"" + awards + "\","
                + "\"poster\":\"" + poster + "\","
                + "\"ratings\":" + ratings + ","
                + "\"metascore\":\"" + metascore + "\","
                + "\"imdbRating\":\"" + imdbRating + "\","
                + "\"imdbVotes\":\"" + imdbVotes + "\","
                + "\"imdbID\":\"" + imdbID + "\","
                + "\"type\":\"" + type + "\","
                + "\"totalSeasons\":\"" + totalSeasons + "\","
                + "\"response\":\"" + response + "\""
                + "}";
    }

    public String getResponse() {
        return this.response;
    }
}
