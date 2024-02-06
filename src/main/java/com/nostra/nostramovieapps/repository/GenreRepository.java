package com.nostra.nostramovieapps.repository;

import com.nostra.nostramovieapps.entity.genre.Genre;
import com.nostra.nostramovieapps.entity.genre.GenreProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>, JpaSpecificationExecutor<Genre> {

    @Query("SELECT g.id as id, g.name as name FROM GENRE g WHERE g.id = :id")
    GenreProjection getIdAndNameGenre(Long id);
}
