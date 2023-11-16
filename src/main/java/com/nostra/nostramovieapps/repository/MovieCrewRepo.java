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

    @Query("SELECT m.title as title, m.overview as overview, m.voteAverage as voteAverage, x.job as job, p.name as director " +
            "FROM MOVIE_CREW x left outer join MOVIE m on m.id = x.person.id " +
            "left outer join PERSON p on p.id = x.person.id WHERE m.title like :search")
    List<Object[]> getMovieDetailByTitle(String search);

    /*@Query(value = "select m.*, mc.job, g.name as genre, p.name as director " +
            "from nostra_movie.nostra.movie m " +
            "left join nostra_movie.nostra.movie_crew mc on mc.id_movie = m.id " +
            "left join nostra_movie.nostra.movie_genre mg on mg.id_movie = m.id " +
            "left join nostra_movie.nostra.genre g on g.id = mg.id_genre " +
            "left join nostra_movie.nostra.person p on p.id = mc.id_person " +
            "where (m.title like :search or g.name like :search)", nativeQuery = true)*/
    @Query("SELECT m.id as id, m.title as title, m.overview as overview, m.voteAverage as voteAverage, mc.job as job," +
            "g.name as genre, p.name as person " +
            "FROM MOVIE m left outer join MOVIE_CREW mc on mc.movie.id = m.id " +
            "left outer join MOVIE_GENRE mg on mg.genre.id = m.id left outer join GENRE g on g.id = mg.genre.id " +
            "left outer join PERSON p on p.id = mc.person.id WHERE (m.title like :search or g.name like :search)")
    List<Object[]> getMovieByGenre(String search);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM nostra_movie.nostra.movie_crew mc where mc.id_movie = :id", nativeQuery = true)
    void deleteMovieCrewById(@Param("id") Long id);
}
