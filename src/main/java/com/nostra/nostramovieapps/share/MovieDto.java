package com.nostra.nostramovieapps.share;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {

    private Long id;
    private String title;
    private String overview;
    private Double voteAverage;
    private String job;
    private String director;
    private String genre;
}
