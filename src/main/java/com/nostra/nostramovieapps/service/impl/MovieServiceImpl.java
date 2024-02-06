package com.nostra.nostramovieapps.service.impl;

import com.nostra.nostramovieapps.dto.genre.MovieGenreDTO;
import com.nostra.nostramovieapps.dto.movie.MovieDTO;
import com.nostra.nostramovieapps.dto.movie.MovieDetailDTO;
import com.nostra.nostramovieapps.dto.search.Filter;
import com.nostra.nostramovieapps.dto.search.SearchRequest;
import com.nostra.nostramovieapps.dto.search.SearchResult;
import com.nostra.nostramovieapps.entity.enums.Status;
import com.nostra.nostramovieapps.entity.genre.Genre;
import com.nostra.nostramovieapps.entity.genre.GenreProjection;
import com.nostra.nostramovieapps.entity.genre.MovieGenre;
import com.nostra.nostramovieapps.entity.movie.Movie;
import com.nostra.nostramovieapps.entity.movie.MovieDetail;
import com.nostra.nostramovieapps.exception.NotFoundException;
import com.nostra.nostramovieapps.repository.GenreRepository;
import com.nostra.nostramovieapps.repository.MovieDetailRepository;
import com.nostra.nostramovieapps.repository.MovieGenreRepository;
import com.nostra.nostramovieapps.repository.MovieRepository;
import com.nostra.nostramovieapps.service.MovieService;
import com.nostra.nostramovieapps.util.QueryGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieDetailRepository movieDetailRepository;

    @Autowired
    MovieGenreRepository movieGenreRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    private QueryGenerator queryGenerator;

    @Override
    public MovieDTO findBy(Long id) {
        return convertToMovieDTO(checkIfRecordExist(id));
    }

    @Override
    @Transactional
    public MovieDTO createMovie(MovieDTO input) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(input, movie);
        movie.setStatus(Status.ACTIVE);
        movie.setCreatedBy("Arya");
        movie.setCreatedAt(ZonedDateTime.now());
        Movie saved = movieRepository.save(movie);

        if (!CollectionUtils.isEmpty(input.getMovieDetails())) {
            for (MovieDetailDTO detail : input.getMovieDetails()) {
                MovieDetail movieDetail = new MovieDetail();
                BeanUtils.copyProperties(detail, movieDetail);
                movieDetail.setMovie(saved);
                movieDetailRepository.save(movieDetail);
            }
        }

        if (!CollectionUtils.isEmpty(input.getMovieGenres())) {
            for (MovieGenreDTO detail : input.getMovieGenres()) {
                MovieGenre movieGenre = new MovieGenre();
                Long genreId = detail.getGenre().getId();
                BeanUtils.copyProperties(detail, movieGenre);
                Optional<Genre> optionalGenre = genreRepository.findById(genreId);
                if (optionalGenre.isPresent()) {
                    movieGenre.setMovie(saved);
                    movieGenre.setGenre(optionalGenre.get());
                    movieGenreRepository.save(movieGenre);
                }
            }
        }

        input.setId(saved.getId());
        input.setVersion(saved.getVersion());
        return input;
    }

    @Override
    public SearchResult<MovieDTO> searchBy(SearchRequest searchRequest) {
        Specification<Movie> specs = where(queryGenerator.createDefaultSpec());

        if (!CollectionUtils.isEmpty(searchRequest.getFilters())) {
            for (Filter filter : searchRequest.getFilters()) {
                specs = specs.and(queryGenerator.createSpecification(filter));
            }
        }

        Page<Movie> pgMovie = movieRepository.findAll(specs, queryGenerator.constructPageable(searchRequest));

        SearchResult<MovieDTO> result = new SearchResult<>();
        result.setTotalRows(pgMovie.getTotalElements());
        result.setTotalPages(pgMovie.getTotalPages());
        result.setCurrentPageNumber(pgMovie.getPageable().getPageNumber());
        result.setCurrentPageSize(pgMovie.getNumberOfElements());
        result.setRows(pgMovie.getContent().stream().map(movie -> convertToMovieDTO(movie)).collect(Collectors.toList()));

        return result;
    }

    @Override
    @Transactional
    public MovieDTO editMovie(MovieDTO input) {
        Movie found = checkIfRecordExist(input.getId());
        BeanUtils.copyProperties(input, found);
        found.setUpdatedBy("Arya");
        found.setUpdatedAt(ZonedDateTime.now());
        found.setStatus(Status.ACTIVE);
        movieRepository.save(found);

        if (!CollectionUtils.isEmpty(input.getMovieDetails())) {
            for (MovieDetailDTO movieDetailDTO : input.getMovieDetails()) {
                Long movieDetId = movieDetailDTO.getId();
                Optional<MovieDetail> optionalMovieDet = movieDetailRepository.findById(movieDetId);
                if (optionalMovieDet.isPresent()) {
                    MovieDetail movieDetail = optionalMovieDet.get();
                    movieDetail.setOverview(movieDetailDTO.getOverview());
                    movieDetail.setReleaseDate(movieDetailDTO.getReleaseDate());
                    movieDetail.setVoteAverage(movieDetailDTO.getVoteAverage());
                    movieDetail.setBackdropPath(movieDetailDTO.getBackdropPath());
                    movieDetail.setPosterPath(movieDetailDTO.getPosterPath());
                    movieDetail.setTrailerLink(movieDetailDTO.getTrailerLink());
                    movieDetailRepository.save(movieDetail);
                }
            }
        }

        if (!CollectionUtils.isEmpty(input.getMovieGenres())) {
            for (MovieGenreDTO movieGenrelDTO : input.getMovieGenres()) {
                MovieGenre movieGenre = new MovieGenre();
                Long genreId = movieGenrelDTO.getGenre().getId();
                Optional<Genre> optionalGenre = genreRepository.findById(genreId);
                if (optionalGenre.isPresent()) {
                    movieGenre.setGenre(optionalGenre.get());
                    movieGenreRepository.save(movieGenre);
                }
            }
        }

        return input;
    }

    @Override
    public void deleteMovie(Long id) {
        Movie movie = checkIfRecordExist(id);
        movie.setStatus(Status.DELETED);
        movie.setUpdatedBy("Arya");
        movie.setUpdatedAt(ZonedDateTime.now());
        movieRepository.save(movie);
    }

    private Movie checkIfRecordExist(Long id) {
        Optional<Movie> optionalItem = movieRepository.findById(id);

        if (!optionalItem.isPresent()) {
            throw new NotFoundException("Record is not found");
        }
        return optionalItem.get();
    }

    private MovieDTO convertToMovieDTO(Movie movie) {
        Set<MovieDetailDTO> movieDetails = new HashSet<>();
        Set<MovieGenreDTO> movieGenres = new HashSet<>();
        if (!CollectionUtils.isEmpty(movie.getMovieDetails())) {
            movieDetails = movie.getMovieDetails().stream().map(detail -> {
                MovieDetailDTO movieDetail = MovieDetailDTO.builder().build();
                BeanUtils.copyProperties(detail, movieDetail);
                return movieDetail;
            }).collect(Collectors.toSet());
        }
        if (!CollectionUtils.isEmpty(movie.getMovieGenres())) {
            movieGenres = movie.getMovieGenres().stream().map(detail -> {
                MovieGenreDTO movieGenre = MovieGenreDTO.builder().build();
                GenreProjection genreProjection = genreRepository.getIdAndNameGenre(detail.getGenre().getId());
                if (genreProjection != null) {
                    movieGenre = MovieGenreDTO.builder().genreId(genreProjection.getId()).name(genreProjection.getName()).build();
                }
                BeanUtils.copyProperties(detail, movieGenre);
                return movieGenre;
            }).collect(Collectors.toSet());
        }
        MovieDTO movieDTO = MovieDTO.builder().build();
        BeanUtils.copyProperties(movie.getId(), movieDTO);
        BeanUtils.copyProperties(movie, movieDTO);
        movieDTO.setMovieDetails(movieDetails);
        movieDTO.setMovieGenres(movieGenres);
        return movieDTO;
    }
}
