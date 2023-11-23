package com.nostra.nostramovieapps.repository;

import com.nostra.nostramovieapps.entity.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>, JpaSpecificationExecutor<Genre> {

    /*@Query("SELECT x FROM Genre x WHERE x.name like :name")
    Optional<Genre> findGenreByNameLike(String name);*/
}
