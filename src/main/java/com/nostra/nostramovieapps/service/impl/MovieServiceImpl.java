package com.nostra.nostramovieapps.service.impl;

import com.nostra.nostramovieapps.entity.*;
import com.nostra.nostramovieapps.repository.*;
import com.nostra.nostramovieapps.service.MovieService;
import com.nostra.nostramovieapps.share.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepo movieRepo;

    @Autowired
    GenreRepo genreRepo;

    @Autowired
    MovieCrewRepo movieCrewRepo;

    @Autowired
    MovieGenreRepo movieGenreRepo;

    @Autowired
    PersonRepo personRepo;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    @Override
    @Transactional
    public Map<String, Object> insertMovies(Movie movie) {

        Optional<Movie> moviesOptional = movieRepo.findMovieByTitleLike(movie.getTitle());
        List<MovieDto> movieDtos = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (moviesOptional.isPresent()) {
            System.out.println("movie optional = " + moviesOptional.get());
            map.put("contentData", "Movie with given title already exists !");
            try {
                throw new Exception("Movie with given title already exists");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            movieRepo.save(movie);
            MovieDto dto = new MovieDto();
            if (movie.getMovieCrewList() != null) {
                for (int i = 0; i < movie.getMovieCrewList().size(); i++) {
                    Optional<Person> personOptional = personRepo.findPersonByNameLike(movie.getMovieCrewList().get(i).getPerson().getName());
                    MovieCrew movieCrew = new MovieCrew();
                    movieCrew.setMovie(movie);
                    movieCrew.setJob(movie.getMovieCrewList().get(i).getJob());
                    movieCrew.setPerson(personOptional.get());
                    movieCrewRepo.save(movieCrew);
                    String[] array = new String[movie.getMovieCrewList().size()];
                    for (int x = 0; x < movie.getMovieCrewList().size(); x++) {
                        array[x] = String.valueOf(movie.getMovieCrewList().get(x).getPerson().getName());
                    }
                    array = Arrays.stream(array).filter(s -> (s != null && s.length() > 0)).toArray(String[]::new);
                    String resultPerson = String.join(", ", array);
                    dto.setPerson(resultPerson);
                    dto.setJob(movieCrew.getJob());
                }
            }

            if (movie.getMovieGenreList() != null) {
                for (int i = 0; i < movie.getMovieGenreList().size(); i++) {
                    Optional<Genre> genreOptional = genreRepo.findGenreByNameLike(movie.getMovieGenreList().get(i).getGenre().getName());
                    MovieGenre movieGenre = new MovieGenre();
                    movieGenre.setMovie(movie);
                    movieGenre.setGenre(genreOptional.get());
                    movieGenreRepo.save(movieGenre);
                    String[] array = new String[movie.getMovieGenreList().size()];
                    for (int x = 0; x < movie.getMovieGenreList().size(); x++) {
                        array[x] = String.valueOf(movie.getMovieGenreList().get(x).getGenre().getName());
                    }
                    array = Arrays.stream(array).filter(s -> (s != null && s.length() > 0)).toArray(String[]::new);
                    String resultGenre = String.join(", ", array);
                    dto.setGenre(resultGenre);
                }
            }

            dto.setId(movie.getId());
            dto.setTitle(movie.getTitle());
            dto.setOverview(movie.getOverview());
            dto.setVoteAverage(movie.getVoteAverage());
            dto.setReleaseDate(movie.getReleaseDate());
            dto.setPosterPath(movie.getPosterPath());
            dto.setBackdropPath(movie.getBackdropPath());
            dto.setTrailerLink(movie.getTrailerLink());
            movieDtos.add(dto);
            map.put("contentData", movieDtos);
        }
        map.put("totalRecords", (long) movieDtos.size());
        return map;
    }

    @Override
    @Transactional
    public Map<String, Object> insertPerson(Person person) {

        Optional<Person> personOptional = personRepo.findPersonByNameLike(person.getName());
        List<MovieDto> movieDtos = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (personOptional.isPresent()) {
            System.out.println("person optional = " + personOptional.get());
            map.put("contentData", "Person with given name already exists !");
            try {
                throw new Exception("Person with given name already exists");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            personRepo.save(person);
            MovieDto dto = new MovieDto();
            dto.setPerson(person.getName());
            movieDtos.add(dto);
            map.put("contentData", person);
        }
        map.put("totalRecords", (long) movieDtos.size());
        return map;
    }

    @Override
    @Transactional
    public Map<String, Object> insertGenre(Genre genre) {

        Optional<Genre> genreOptional = genreRepo.findGenreByNameLike(genre.getName());
        List<MovieDto> movieDtos = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (genreOptional.isPresent()) {
            System.out.println("genre optional = " + genreOptional.get());
            map.put("contentData", "Genre with given genre name already exists !");
            try {
                throw new Exception("Genre with given genre name already exists");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            genreRepo.save(genre);
            MovieDto dto = new MovieDto();
            dto.setGenre(genre.getName());
            movieDtos.add(dto);
            map.put("contentData", genre);
        }
        map.put("totalRecords", (long) movieDtos.size());
        return map;
    }

    @Override
    public Map<String, Object> getMovieByGenre(Map<String, Object> mapInput, String search) {

        if (search.equals("")) {
            search = "%%";
        } else {
            search = "%" + search + "%";
        }
        List<Object[]> objectsMovieByGenre = movieCrewRepo.getMovieByGenre(search);
        List<MovieDto> movieDtos = new ArrayList<>();
        for (Object[] data : objectsMovieByGenre) {
            MovieDto dto = new MovieDto();
            dto.setId((Long) data[0]);
            dto.setTitle((String) data[1]);
            dto.setOverview((String) data[2]);
            dto.setVoteAverage((Double) data[3]);
            dto.setJob((String) data[4]);
            dto.setGenre((String) data[5]);
            dto.setPerson((String) data[6]);
            movieDtos.add(dto);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("contentData", movieDtos);
        map.put("totalRecords", (long) movieDtos.size());
        return map;
    }

    @Override
    public Map<String, Object> getMovieByTitle(Map<String, Object> mapInput, String search) {

        if (search.equals("")) {
            search = "%%";
        } else {
            search = "%" + search + "%";
        }
        List<Object[]> objectsMovie = movieCrewRepo.getMovieDetailByTitle(search);
        List<Object[]> objectsGenre = movieGenreRepo.getGenreByTitle(search);
        List<MovieDto> movieDtos = new ArrayList<>();
        List<MovieDto> genreDtos = new ArrayList<>();
        for (Object[] dataGenre : objectsGenre) {
            MovieDto dto = new MovieDto();
            dto.setId((long) dataGenre[0]);
            dto.setGenre((String) dataGenre[1]);
            genreDtos.add(dto);
        }
        for (Object[] data : objectsMovie) {
            MovieDto dto = new MovieDto();
            dto.setId((Long) data[0]);
            dto.setTitle((String) data[1]);
            dto.setOverview((String) data[2]);
            dto.setVoteAverage((Double) data[3]);
            dto.setJob((String) data[4]);
            dto.setPerson((String) data[5]);
            String[] array = new String[genreDtos.size()];
            for (int x = 0; x < genreDtos.size(); x++) {
                if (genreDtos.get(x).getId().equals(dto.getId())) {
                    array[x] = String.valueOf(genreDtos.get(x).getGenre());
                }
            }
            array = Arrays.stream(array).filter(s -> (s != null && s.length() > 0)).toArray(String[]::new);
            String resultGenre = String.join(", ", array);
            dto.setGenre(resultGenre);
            movieDtos.add(dto);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("contentData", movieDtos);
        map.put("totalRecords", (long) movieDtos.size());
        return map;
    }

    @Override
    public Map<String, Object> deleteMovieById(Long id) {

        Optional<Movie> movieOptional = movieRepo.findById(id);
        Optional<MovieCrew> movieCrewOptional = movieCrewRepo.findByMovieId(movieOptional.get().getId());
        List<MovieGenre> movieGenreList = movieGenreRepo.findByMovieId(movieOptional.get().getId());
        List<MovieDto> movieDtos = new ArrayList<>();
        if (!movieOptional.isPresent()) {
            try {
                throw new Exception("Movie with " + id + " not found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (movieCrewOptional.isPresent() && movieGenreList != null) {
                Movie movie = movieOptional.get();
                movieCrewRepo.deleteMovieCrewById(movie.getId());
                movieGenreRepo.deleteMovieGenreById(movie.getId());
                movieRepo.deleteMovieById(movie.getId());
                MovieDto dto = new MovieDto();
                dto.setId(id);
                dto.setTitle(movie.getTitle());
                dto.setOverview(movie.getOverview());
                dto.setVoteAverage(movie.getVoteAverage());
                movieDtos.add(dto);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("contentData", movieDtos);
        map.put("totalRecords", (long) movieDtos.size());
        return map;
    }

    @Override
    public void deleteAllMovie() {
        movieRepo.deleteAll();
    }

    @Override
    public Optional<Movie> findById(long id) {
        return movieRepo.findById(id);
    }

    @Override
    @Transactional
    public Map<String, Object> updateMovieById(Map<String, Object> mapInput, Long id, String title, String overview, Double voteAverage) {

        Optional<Movie> movieWithId = movieRepo.findById(id);
        Optional<Movie> movieWithSameTitle = movieRepo.findMovieByTitleLike(title);
        List<MovieDto> movieDtos = new ArrayList<>();
        if (movieWithId.isPresent()) {
            if (movieWithSameTitle.isPresent()) {
                try {
                    throw new Exception("Movie with given title already exists");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            movieRepo.updateMovieById(id, title, overview, voteAverage);
            MovieDto dto = new MovieDto();
            dto.setId(id);
            dto.setTitle(title);
            dto.setOverview(overview);
            dto.setVoteAverage(voteAverage);
            movieDtos.add(dto);
        } else {
            try {
                throw new Exception("Movie with " + id + " not found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("contentData", movieDtos);
        map.put("totalRecords", (long) movieDtos.size());
        return map;
    }
}
