package com.nostra.nostramovieapps.dto.movie;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nostra.nostramovieapps.dto.base.BaseDTO;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDetailDTO extends BaseDTO {
    private String overview;
    private Double voteAverage;
    private String releaseDate;
    private String posterPath;
    private String backdropPath;
    private String trailerLink;
}
