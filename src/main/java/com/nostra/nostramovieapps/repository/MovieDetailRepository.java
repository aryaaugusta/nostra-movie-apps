package com.nostra.nostramovieapps.repository;

import com.nostra.nostramovieapps.entity.movie.MovieDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDetailRepository extends JpaRepository<MovieDetail, Long>, JpaSpecificationExecutor<MovieDetail> {
}
