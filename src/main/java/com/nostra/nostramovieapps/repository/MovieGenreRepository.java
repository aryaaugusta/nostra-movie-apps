package com.nostra.nostramovieapps.repository;

import com.nostra.nostramovieapps.entity.genre.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long>, JpaSpecificationExecutor<MovieGenre> {
}
