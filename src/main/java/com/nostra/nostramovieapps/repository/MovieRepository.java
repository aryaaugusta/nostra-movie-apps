package com.nostra.nostramovieapps.repository;

import com.nostra.nostramovieapps.entity.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    /*@Query("SELECT x FROM Movie x WHERE x.title like :title")
    Optional<Movie> findMovieByTitleLike(String title);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE nostra_movie.nostra.movie SET title = :title, overview = :overview, vote_average = :voteAverage where id = :id", nativeQuery = true)
    void updateMovieById(@Param("id") Long id, @Param("title") String title, @Param("overview") String overview, @Param("voteAverage") Double voteAverage);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM nostra_movie.nostra.movie m where m.id = :id", nativeQuery = true)
    void deleteMovieById(@Param("id") Long id);*/
}
