package com.nostra.nostramovieapps.repository;

import com.nostra.nostramovieapps.entity.MovieCrew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieCrewRepo extends JpaRepository<MovieCrew, Long> {

    @Query("SELECT x.person.name as name FROM MOVIE_CREW x left outer join MOVIE m on m.id = x.person.id WHERE m.id = :id")
    Optional<MovieCrew> findByMovieId(Long id);

    @Query("SELECT m.id as id, m.title as title, m.overview as overview, m.voteAverage as voteAverage, " +
            "m.backdropPath as backdropPath, m.posterPath as posterPath, m.releaseDate as releaseDate, m.trailerLink as trailerLink, " +
            "x.job as job, x.person.name as director FROM MOVIE_CREW x left outer join MOVIE m on m.id = x.movie.id " +
            "WHERE lower(m.title) LIKE CONCAT('%',:search,'%')")
    List<Object[]> getMovieDetailByTitle(String search);

    @Query("SELECT m.id as id, m.title as title, m.overview as overview, m.voteAverage as voteAverage, " +
            "m.backdropPath as backdropPath, m.posterPath as posterPath, m.releaseDate as releaseDate, m.trailerLink as trailerLink, " +
            "x.job as job, x.person.name as director, g.name as genre FROM MOVIE_CREW x left outer join MOVIE m on m.id = x.movie.id " +
            "left outer join MOVIE_GENRE mg on mg.movie.id = m.id left outer join GENRE g on g.id = mg.genre.id " +
            "WHERE lower(g.name) LIKE CONCAT('%',:search,'%')")
    List<Object[]> getMovieDetailByGenre(String search);

    @Query("SELECT x.movie.id as id, x.genre.name as genre FROM MOVIE_GENRE x left outer join MOVIE m on m.id = x.movie.id " +
            "WHERE lower(x.genre.name) LIKE CONCAT('%',:search,'%')")
    List<Object[]> getMovieByGenre(String search);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM nostra_movie.nostra.movie_crew mc where mc.id_movie = :id", nativeQuery = true)
    void deleteMovieCrewById(@Param("id") Long id);
}
