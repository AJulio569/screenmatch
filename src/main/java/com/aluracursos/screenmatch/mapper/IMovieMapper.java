package com.aluracursos.screenmatch.mapper;

import com.aluracursos.screenmatch.dto.response.RatingResponse;
import com.aluracursos.screenmatch.dto.response.MovieResponse;
import com.aluracursos.screenmatch.entity.MovieEntity;
import com.aluracursos.screenmatch.entity.RatingsEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IMovieMapper {
    MovieResponse toResponse(MovieEntity entity);
    RatingResponse toRatingResponse(RatingsEntity ratingsEntity);
    List<RatingResponse> toRatingResponseList(List<RatingsEntity> ratingsEntity);
}
