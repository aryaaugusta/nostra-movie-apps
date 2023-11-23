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
    /*

    @ApiOperation(value = "Get Movie By Title", notes = "This method for get all movie or filter or search movie by title")
    @ApiImplicitParams(@ApiImplicitParam(name = "mapInput", dataType = "ApplicationProperties2",
            value = "Input movie title name value for the movie title you need to search", example = "Jurassic", required = true,
            examples = @Example(@ExampleProperty(mediaType = "application/json", value = "{\"search\":\"Jurassic\"}"))))
    @PostMapping("/getMovieByTitle")
    public RestResponse getMovieByTitle(@RequestBody Map<String, Object> mapInput) {

        try {
            String search = (String) mapInput.get("search");
            Map<String, Object> map = movieService.getMovieByTitle(mapInput, search);
            if (map.get("totalRecords").toString().equalsIgnoreCase("0")) {
                return new RestResponse(CommonConstants.NO_CONTENT, "No Content", map.get("contentData"), (long) map.get("totalRecords"));
            }
            return new RestResponse(CommonConstants.OK_REST_STATUS, "OK", map.get("contentData"), (long) map.get("totalRecords"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed get movie by title", e);
        }
    }


    @ApiOperation(value = "Get Movie By Genre", notes = "This method for filter or search all movie by genre")
    @ApiImplicitParams(@ApiImplicitParam(name = "mapInput", dataType = "ApplicationProperties1",
            value = "Input movie genre name value for the movie you need to search by movie genre/category", example = "Action", required = true,
            examples = @Example(@ExampleProperty(mediaType = "application/json", value = "{\"search\":\"Action\"}"))))
    @PostMapping("/getMovieByGenre")
    public RestResponse getMovieByGenre(@RequestBody Map<String, Object> mapInput) {

        try {
            String search = (String) mapInput.get("search");
            Map<String, Object> map = movieService.getMovieByGenre(mapInput, search);
            if (map.get("totalRecords").toString().equalsIgnoreCase("0")) {
                return new RestResponse(CommonConstants.NO_CONTENT, "No Content", map.get("contentData"), (long) map.get("totalRecords"));
            }
            return new RestResponse(CommonConstants.OK_REST_STATUS, "OK", map.get("contentData"), (long) map.get("totalRecords"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed get movie by genre", e);
        }
    }

    @ApiOperation(value = "Get All Movie", notes = "This method for get all movie")
    @GetMapping("/allMovie")
    public ResponseEntity getAllMovies() {
        try {
            List<Movie> movies = movieService.getAllMovies();
            if (movies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<MovieDTO> get(@PathVariable Long id) {
        try {
            String search = (String) mapInput.get("search");
            Map<String, Object> map = movieService.findById(mapInput, search);
            if (map.get("totalRecords").toString().equalsIgnoreCase("0")) {
                return new RestResponse(CommonConstants.NO_CONTENT, "No Content", map.get("contentData"), (long) map.get("totalRecords"));
            }
            return new RestResponse(CommonConstants.OK_REST_STATUS, "OK", map.get("contentData"), (long) map.get("totalRecords"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed get movie by genre", e);
        }
    }

    @ApiOperation(value = "Delete Movie by Id Movie", notes = "This method for delete movie by id movie")
    @DeleteMapping("/deleteMovie/{id}")
    public RestResponse deleteMovieById(@ApiParam(value = "ID Movie value for the movie you need to deleted", required = true) @PathVariable("id") Long id) {
        try {
            Map<String, Object> map = movieService.deleteMovieById(id);
            if (map.get("totalRecords").toString().equalsIgnoreCase("0")) {
                return new RestResponse(CommonConstants.NO_CONTENT, "No Content", map.get("contentData"), (long) map.get("totalRecords"));
            }
            return new RestResponse(CommonConstants.OK_REST_STATUS, "Delete Movie Successfully !", map.get("contentData"), (long) map.get("totalRecords"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed delete movie by id", e);
        }
    }

    @ApiOperation(value = "Delete All Movie", notes = "This method for delete all movie")
    @DeleteMapping("/deleteAllMovie")
    public ResponseEntity<HttpStatus> deleteAllMovie() {
        try {
            movieService.deleteAllMovie();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update Movie by Id Movie", notes = "This method for update movie by id movie")
    @ApiImplicitParams(@ApiImplicitParam(name = "mapInput", dataType = "ApplicationProperties3", value = "Data to be stored in Json format", required = true,
            examples = @Example(@ExampleProperty(mediaType = "application/json", value = "{}"))))
    @PutMapping("/updateMovie/{id}")
    public RestResponse updateMovieById(@ApiParam(value = "ID Movie value for the movie you need to updated", required = true)
                                        @PathVariable(value = "id") Long id,
                                        @ApiParam(value = "Data to be stored in Json format", required = true) @RequestBody Map<String, Object> mapInput) {

        try {
            Optional<Movie> moviesData = movieService.findById(id);
            if (moviesData.isPresent()) {
                String title = (String) mapInput.get("title");
                String overview = (String) mapInput.get("overview");
                Double voteAverage = (Double) mapInput.get("voteAverage");
                Map<String, Object> map = movieService.updateMovieById(mapInput, id, title, overview, voteAverage);
                if (map.get("totalRecords").toString().equalsIgnoreCase("0")) {
                    return new RestResponse(CommonConstants.NO_CONTENT, "No Content", map.get("contentData"), (long) map.get("totalRecords"));
                }
                return new RestResponse(CommonConstants.OK_REST_STATUS, "Update Movie Successfully !", map.get("contentData"), (long) map.get("totalRecords"));
            } else {
                return new RestResponse(CommonConstants.NO_CONTENT, "No Content", new HashMap<String, Object>(), 0L);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed update movie by id", e);
        }
    }*/
}
