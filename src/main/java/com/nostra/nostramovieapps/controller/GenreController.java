package com.nostra.nostramovieapps.controller;

import com.nostra.nostramovieapps.dto.genre.GenreDTO;
import com.nostra.nostramovieapps.dto.search.SearchRequest;
import com.nostra.nostramovieapps.dto.search.SearchResult;
import com.nostra.nostramovieapps.service.GenreService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nostramovie/genre")
@Api(value = "Nostra Movie Apps API", description = "API to control genre in Nostra Movie Apps")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreDTO> create(@RequestBody GenreDTO genre) {
        return new ResponseEntity<>(genreService.createGenre(genre), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<GenreDTO> get(@PathVariable Long id) {
        return new ResponseEntity<>(genreService.findBy(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<GenreDTO> edit(@RequestBody GenreDTO movie,
                                         @PathVariable Long id) {
        movie.setId(id);
        return new ResponseEntity<>(genreService.editGenre(movie), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        genreService.deleteGenre(id);
    }

    @PostMapping("search")
    public ResponseEntity<SearchResult<GenreDTO>> search(@RequestBody SearchRequest searchRequest) {
        return new ResponseEntity<>(genreService.searchBy(searchRequest), HttpStatus.OK);
    }
}
