package com.nostra.nostramovieapps.controller;

import com.nostra.nostramovieapps.common.model.RestResponse;
import com.nostra.nostramovieapps.common.util.CommonConstants;
import com.nostra.nostramovieapps.entity.Genre;
import com.nostra.nostramovieapps.entity.Movie;
import com.nostra.nostramovieapps.entity.Person;
import com.nostra.nostramovieapps.service.MovieService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/nostramovie")
@Api(value = "Nostra Movie Apps API", description = "API to control all operation in Nostra Movie Apps")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @ApiOperation(value = "Insert Movie", notes = "This method for save or input movie")
    @PostMapping("/insertMovie")
    public RestResponse createMovies(@ApiParam(value = "Input title,overview,rating value for the movie you need to insert", required = true) @RequestBody Movie movie) {
        try {
            Map<String, Object> map = movieService.insertMovies(movie);
            if (map.get("totalRecords").toString().equalsIgnoreCase("0")) {
                return new RestResponse(CommonConstants.CONFLICT, "Insert Movie Failed !", map.get("contentData"), (long) map.get("totalRecords"));
            }
            return new RestResponse(CommonConstants.CREATED, "Insert Movie Successfully !", map.get("contentData"), (long) map.get("totalRecords"));
        } catch (Exception e) {
            return new RestResponse(CommonConstants.INTERNAL_SERVER_ERROR, "Internal Server Error", new HashMap<String, Object>(), 0L);
        }
    }

    @ApiOperation(value = "Insert Person", notes = "This method for save or input person name")
    @PostMapping("/insertPerson")
    public RestResponse createPerson(@ApiParam(value = "Input person name ex. director name, producer name etc", required = true) @RequestBody Person person) {
        try {
            Map<String, Object> map = movieService.insertPerson(person);
            if (map.get("totalRecords").toString().equalsIgnoreCase("0")) {
                return new RestResponse(CommonConstants.CONFLICT, "Insert Person Failed !", map.get("contentData"), (long) map.get("totalRecords"));
            }
            return new RestResponse(CommonConstants.CREATED, "Insert Person Successfully !", map.get("contentData"), (long) map.get("totalRecords"));
        } catch (Exception e) {
            return new RestResponse(CommonConstants.INTERNAL_SERVER_ERROR, "Internal Server Error", new HashMap<String, Object>(), 0L);
        }
    }

    @ApiOperation(value = "Insert Genre", notes = "This method for save or input genre")
    @PostMapping("/insertGenre")
    public RestResponse createGenre(@ApiParam(value = "Input movie genre", required = true) @RequestBody Genre genre) {
        try {
            Map<String, Object> map = movieService.insertGenre(genre);
            if (map.get("totalRecords").toString().equalsIgnoreCase("0")) {
                return new RestResponse(CommonConstants.CONFLICT, "Insert Genre Failed !", map.get("contentData"), (long) map.get("totalRecords"));
            }
            return new RestResponse(CommonConstants.CREATED, "Insert Genre Successfully !", map.get("contentData"), (long) map.get("totalRecords"));
        } catch (Exception e) {
            return new RestResponse(CommonConstants.INTERNAL_SERVER_ERROR, "Internal Server Error", new HashMap<String, Object>(), 0L);
        }
    }

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
//            return new RestResponse(CommonConstants.INTERNAL_SERVER_ERROR, "Internal Server Error", new HashMap<String, Object>(), 0L);
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
//            return new RestResponse(CommonConstants.INTERNAL_SERVER_ERROR, "Internal Server Error", new HashMap<String, Object>(), 0L);
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
            return new RestResponse(CommonConstants.INTERNAL_SERVER_ERROR, "Internal Server Error", new HashMap<String, Object>(), 0L);
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
            return new RestResponse(CommonConstants.INTERNAL_SERVER_ERROR, "Internal Server Error", new HashMap<String, Object>(), 0L);
        }
    }
}
