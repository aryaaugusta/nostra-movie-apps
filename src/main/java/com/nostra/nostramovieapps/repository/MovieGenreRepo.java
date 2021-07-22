package com.nostra.nostramovieapps.repository;

import com.nostra.nostramovieapps.entity.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface MovieGenreRepo extends JpaRepository<MovieGenre, Long> {

    @Query("SELECT x FROM MovieGenre x left join x.movie m WHERE m.id = :id")
    List<MovieGenre> findByMovieId(Long id);

    @Query("SELECT m.id as id_movie, g.name as genre FROM MovieGenre x left join x.movie m left join x.genre g WHERE m.title like :search")
    List<Object[]> getGenreByTitle(@Param("search") String search);


    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM nostra_movie.nostra.movie_genre mg where mg.id_movie = :id", nativeQuery = true)
    void deleteMovieGenreById(@Param("id") Long id);
}