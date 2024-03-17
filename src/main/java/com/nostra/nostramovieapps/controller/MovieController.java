package com.nostra.nostramovieapps.controller;

import com.nostra.nostramovieapps.dto.movie.MovieDTO;
import com.nostra.nostramovieapps.dto.search.SearchRequest;
import com.nostra.nostramovieapps.dto.search.SearchResult;
import com.nostra.nostramovieapps.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/nostramovie/movie")
@Api(value = "Nostra Movie Apps API", description = "API to control movie in Nostra Movie Apps")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @ApiOperation(value = "create", notes = "This method for create movie")
    @PostMapping
    public ResponseEntity<MovieDTO> create(@ApiParam(value = "Input title, overview, rating value for the movie you need to insert", required = true)
                                           @RequestBody MovieDTO movie) {
        return new ResponseEntity<>(movieService.createMovie(movie), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<MovieDTO> get(@PathVariable Long id) {
        return new ResponseEntity<>(movieService.findBy(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<MovieDTO> edit(@RequestBody MovieDTO movie,
                                         @PathVariable Long id) {
        movie.setId(id);
        return new ResponseEntity<>(movieService.editMovie(movie), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }

    @PostMapping("search")
    public ResponseEntity<SearchResult<MovieDTO>> search(@RequestBody SearchRequest searchRequest) {
        return new ResponseEntity<>(movieService.searchBy(searchRequest), HttpStatus.OK);
    }
}
