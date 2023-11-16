package com.nostra.nostramovieapps.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "MOVIE")
@Data
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(example = "Jurassic World", required = true)
    @Column(name = "TITLE", length = 1000)
    private String title;

    @ApiModelProperty(example = "Twenty-two years after the events of Jurassic Park, Isla Nublar now features a fully functioning dinosaur theme park, Jurassic World, as originally envisioned by John Hammond.", required = true)
    @Column(name = "OVERVIEW", length = 1000)
    private String overview;

    @ApiModelProperty(example = "6.50", required = true)
    @Column(name = "VOTE_AVERAGE")
    private Double voteAverage;

    //    @Transient
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "movieCrewId")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private List<MovieCrew> movieCrewList;

    //    @Transient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    @JsonManagedReference(value = "movieGenreId")
    private List<MovieGenre> movieGenreList;
}
