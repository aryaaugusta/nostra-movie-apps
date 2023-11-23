package com.nostra.nostramovieapps.service;

import com.nostra.nostramovieapps.dto.genre.GenreDTO;
import com.nostra.nostramovieapps.dto.search.SearchRequest;
import com.nostra.nostramovieapps.dto.search.SearchResult;

public interface GenreService {
    GenreDTO createGenre(GenreDTO input);

    GenreDTO findBy(Long id);

    GenreDTO editGenre(GenreDTO input);

    void deleteGenre(Long id);

    SearchResult<GenreDTO> searchBy(SearchRequest searchRequest);
}
