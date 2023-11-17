package com.nostra.nostramovieapps.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "MOVIE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(example = "Jurassic World", required = true)
    @Column(name = "TITLE", length = 1000)
    private String title;

    @ApiModelProperty(example = "Twenty-two years after the events of Jurassic Park, " +
            "Isla Nublar now features a fully functioning dinosaur theme park, Jurassic World, " +
            "as originally envisioned by John Hammond.", required = true)
    @Column(name = "OVERVIEW", length = 1000)
    private String overview;

    @ApiModelProperty(example = "6.50", required = true)
    @Column(name = "VOTE_AVERAGE")
    private Double voteAverage;

    @ApiModelProperty(example = "2018-06-22", required = true)
    @Column(name = "RELEASE_DATE")
    private String releaseDate;

    @Column(name = "POSTER_PATH")
    private String posterPath;

    @Column(name = "BACKDROP_PATH")
    private String backdropPath;

    @Column(name = "TRAILER_LINK")
    private String trailerLink;

    @JsonManagedReference(value = "movieCrewId")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    @ToString.Exclude
    private List<MovieCrew> movieCrewList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    @JsonManagedReference(value = "movieGenreId")
    @ToString.Exclude
    private List<MovieGenre> movieGenreList;
}
