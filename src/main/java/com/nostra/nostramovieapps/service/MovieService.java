package com.nostra.nostramovieapps.service;

import com.nostra.nostramovieapps.dto.movie.MovieDTO;
import com.nostra.nostramovieapps.dto.search.SearchRequest;
import com.nostra.nostramovieapps.dto.search.SearchResult;

public interface MovieService {
    MovieDTO findBy(Long id);

    MovieDTO createMovie(MovieDTO input);

    SearchResult<MovieDTO> searchBy(SearchRequest searchRequest);

    MovieDTO editMovie(MovieDTO input);

    void deleteMovie(Long id);
}
