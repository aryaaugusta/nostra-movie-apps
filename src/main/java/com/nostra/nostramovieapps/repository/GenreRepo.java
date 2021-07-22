package com.nostra.nostramovieapps.repository;

import com.nostra.nostramovieapps.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepo extends JpaRepository<Genre, Long> {

    @Query("SELECT x FROM Genre x WHERE x.name like :name")
    Optional<Genre> findByGenreName(String name);
}
