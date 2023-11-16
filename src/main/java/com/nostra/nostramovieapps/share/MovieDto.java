package com.nostra.nostramovieapps.share;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class MovieDto {

    private Long id;
    private String title;
    private String overview;
    private Double voteAverage;
    private String job;
    private String person;
    private String genre;
}
