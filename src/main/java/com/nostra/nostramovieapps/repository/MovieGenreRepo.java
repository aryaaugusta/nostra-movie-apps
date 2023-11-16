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

    //    @Query("SELECT x FROM MovieGenre x left join x.movie m WHERE m.id = :id")
    @Query("SELECT x.genre.name as genre FROM MOVIE_GENRE x left outer join MOVIE m on m.id = x.movie.id WHERE m.id = :id")
    List<MovieGenre> findByMovieId(Long id);

    //    @Query("SELECT m.id as id_movie, g.name as genre FROM MovieGenre x left join x.movie m left join x.genre g WHERE m.title like :search")
    @Query("SELECT g.genre.name as genre FROM MOVIE_GENRE x left outer join MOVIE m on m.id = x.movie.id " +
            "left outer join MOVIE_GENRE g on g.id = x.genre.id WHERE m.title like :search")
    List<Object[]> getGenreByTitle(String search);


    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM nostra_movie.nostra.movie_genre mg where mg.id_movie = :id", nativeQuery = true)
    void deleteMovieGenreById(@Param("id") Long id);
}
