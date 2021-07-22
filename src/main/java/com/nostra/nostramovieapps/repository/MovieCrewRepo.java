package com.nostra.nostramovieapps.repository;

import com.nostra.nostramovieapps.entity.MovieCrew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface MovieCrewRepo extends JpaRepository<MovieCrew, Long> {

    @Query("SELECT x FROM MovieCrew x left join x.movie m WHERE m.id = :id")
    Optional<MovieCrew> findByMovieId(Long id);

    @Query("SELECT m.id, m.title, m.overview, m.voteAverage, x.job, p.name as director " +
            "FROM MovieCrew x left join x.movie m left join x.person p WHERE m.title like :search")
    List<Object[]> getMovieDetailByTitle(@Param("search") String search);

    @Query(value = "select m.*, mc.job, g.name as genre, p.name as director " +
            "from nostra_movie.nostra.movie m " +
            "left join nostra_movie.nostra.movie_crew mc on mc.id_movie = m.id " +
            "left join nostra_movie.nostra.movie_genre mg on mg.id_movie = m.id " +
            "left join nostra_movie.nostra.genre g on g.id = mg.id_genre " +
            "left join nostra_movie.nostra.person p on p.id = mc.id_person " +
            "where (m.title like :search or g.name like :search)", nativeQuery = true)
    List<Object[]> getMovieByGenre(@Param("search") String search);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM nostra_movie.nostra.movie_crew mc where mc.id_movie = :id", nativeQuery = true)
    void deleteMovieCrewById(@Param("id") Long id);
}
