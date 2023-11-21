package com.nostra.nostramovieapps.repository;

import com.nostra.nostramovieapps.entity.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieGenreRepo extends JpaRepository<MovieGenre, Long> {

    @Query("SELECT x.genre.name as genre FROM MOVIE_GENRE x left outer join MOVIE m on m.id = x.movie.id WHERE m.id = :id")
    List<MovieGenre> findByMovieId(Long id);

    @Query("SELECT x.movie.id as id, g.name as genre FROM MOVIE_GENRE x left outer join MOVIE m on m.id = x.movie.id " +
            "left outer join GENRE g on g.id = x.genre.id WHERE lower(m.title) LIKE CONCAT('%',:search,'%')")
    List<Object[]> getGenreByTitle(String search);


    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM nostra_movie.nostra.movie_genre mg where mg.id_movie = :id", nativeQuery = true)
    void deleteMovieGenreById(@Param("id") Long id);
}
