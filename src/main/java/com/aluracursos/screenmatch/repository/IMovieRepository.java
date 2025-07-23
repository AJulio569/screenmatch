package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IMovieRepository extends JpaRepository<MovieEntity,Long> {
    List<MovieEntity> findByTitleContainingIgnoreCase(String title);

}
