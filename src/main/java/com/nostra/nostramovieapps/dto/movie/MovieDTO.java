package com.nostra.nostramovieapps.dto.movie;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nostra.nostramovieapps.dto.base.BaseDTO;
import com.nostra.nostramovieapps.dto.genre.MovieGenreDTO;
import lombok.*;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDTO extends BaseDTO {
    private String title;
    private Set<MovieDetailDTO> movieDetails;
    private Set<MovieGenreDTO> movieGenres;
}
