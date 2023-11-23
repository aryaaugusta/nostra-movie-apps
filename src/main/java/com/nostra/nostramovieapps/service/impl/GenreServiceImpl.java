package com.nostra.nostramovieapps.service.impl;

import com.nostra.nostramovieapps.dto.genre.GenreDTO;
import com.nostra.nostramovieapps.dto.search.Filter;
import com.nostra.nostramovieapps.dto.search.SearchRequest;
import com.nostra.nostramovieapps.dto.search.SearchResult;
import com.nostra.nostramovieapps.entity.enums.Status;
import com.nostra.nostramovieapps.entity.genre.Genre;
import com.nostra.nostramovieapps.exception.NotFoundException;
import com.nostra.nostramovieapps.repository.GenreRepository;
import com.nostra.nostramovieapps.service.GenreService;
import com.nostra.nostramovieapps.util.QueryGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    private QueryGenerator queryGenerator;

    @Override
    @Transactional
    public GenreDTO createGenre(GenreDTO input) {
        Genre genre = new Genre();
        BeanUtils.copyProperties(input, genre);
        genre.setStatus(Status.ACTIVE);
        genre.setCreatedBy("Arya");
        genre.setCreatedAt(ZonedDateTime.now());
        Genre saved = genreRepository.save(genre);
        input.setId(saved.getId());
        input.setVersion(saved.getVersion());
        return input;
    }

    @Override
    public GenreDTO findBy(Long id) {
        return convertToGenreDTO(checkIfRecordExist(id));
    }

    @Override
    public GenreDTO editGenre(GenreDTO input) {
        Genre found = checkIfRecordExist(input.getId());
        BeanUtils.copyProperties(input, found);
        found.setUpdatedBy("Arya");
        found.setUpdatedAt(ZonedDateTime.now());
        found.setStatus(Status.ACTIVE);
        genreRepository.save(found);
        return input;
    }

    @Override
    public void deleteGenre(Long id) {
        Genre genre = checkIfRecordExist(id);
        genre.setStatus(Status.DELETED);
        genre.setUpdatedBy("Arya");
        genre.setUpdatedAt(ZonedDateTime.now());
        genreRepository.save(genre);
    }

    @Override
    public SearchResult<GenreDTO> searchBy(SearchRequest searchRequest) {
        Specification<Genre> specs = where(queryGenerator.createDefaultSpec());

        if (!CollectionUtils.isEmpty(searchRequest.getFilters())) {
            for (Filter filter : searchRequest.getFilters()) {
                specs = specs.and(queryGenerator.createSpecification(filter));
            }
        }

        Page<Genre> pgGenre = genreRepository.findAll(specs, queryGenerator.constructPageable(searchRequest));

        SearchResult<GenreDTO> result = new SearchResult<>();
        result.setTotalRows(pgGenre.getTotalElements());
        result.setTotalPages(pgGenre.getTotalPages());
        result.setCurrentPageNumber(pgGenre.getPageable().getPageNumber());
        result.setCurrentPageSize(pgGenre.getNumberOfElements());
        result.setRows(pgGenre.getContent().stream().map(movie -> convertToGenreDTO(movie)).collect(Collectors.toList()));

        return result;
    }

    private Genre checkIfRecordExist(Long id) {
        Optional<Genre> optionalItem = genreRepository.findById(id);

        if (!optionalItem.isPresent()) {
            throw new NotFoundException("Record is not found");
        }
        return optionalItem.get();
    }

    private GenreDTO convertToGenreDTO(Genre genre) {
        GenreDTO genreDTO = GenreDTO.builder().build();
        BeanUtils.copyProperties(genre.getId(), genreDTO);
        BeanUtils.copyProperties(genre, genreDTO);
        return genreDTO;
    }
}
