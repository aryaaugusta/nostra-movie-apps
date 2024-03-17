package com.nostra.nostramovieapps.entity.movie;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "MOVIE_DET")
@Data
@NoArgsConstructor
public class MovieDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(example = "Twenty-two years after the events of Jurassic Park, " +
            "Isla Nublar now features a fully functioning dinosaur theme park, Jurassic World, " +
            "as originally envisioned by John Hammond.", required = true)
    @Column(name = "OVERVIEW", length = 1000)
    private String overview;

    @ApiModelProperty(example = "6.50", required = true)
    private Double voteAverage;

    @ApiModelProperty(example = "2018-06-22", required = true)
    private String releaseDate;

    @ApiModelProperty(example = "/A0LZHXUzo5C60Oahvt7VxvwuzHw.jpg", required = true)
    private String posterPath;

    @ApiModelProperty(example = "/dF6FjTZzRTENfB4R17HDN20jLT2.jpg", required = true)
    private String backdropPath;

    @ApiModelProperty(example = "https://www.youtube.com/watch?v=RFinNxS5KN4", required = true)
    private String trailerLink;

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    private Movie movie;
}
