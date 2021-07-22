package com.nostra.nostramovieapps.service;

import com.nostra.nostramovieapps.entity.Genre;
import com.nostra.nostramovieapps.entity.Movie;
import com.nostra.nostramovieapps.entity.Person;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MovieService {

    List<Movie> getAllMovies();

    Movie insertMovies(Movie movie);

    Map<String, Object> deleteMovieById(Long id);

    void deleteAllMovie();

    Optional<Movie> findById(long id);

    Person insertPerson(Person person);

    Genre insertGenre(Genre genre);

    Map<String, Object> getMovieByTitle(Map<String, Object> mapInput, String title);

    Map<String, Object> getMovieByGenre(Map<String, Object> mapInput, String search);

    Map<String, Object> updateMovieById(Map<String, Object> mapInput, Long id, String title, String overview, Double voteAverage);

}
