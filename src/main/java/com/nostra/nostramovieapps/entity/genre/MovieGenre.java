package com.nostra.nostramovieapps.entity.genre;

import com.nostra.nostramovieapps.entity.movie.Movie;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "MOVIE_GENRE")
@Data
@NoArgsConstructor
public class MovieGenre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GENRE_ID", nullable = false)
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    private Movie movie;
}
