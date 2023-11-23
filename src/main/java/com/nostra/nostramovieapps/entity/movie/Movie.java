package com.nostra.nostramovieapps.entity.movie;

import com.nostra.nostramovieapps.entity.base.BaseEntity;
import com.nostra.nostramovieapps.entity.genre.MovieGenre;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "MOVIE")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Movie extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;

    @Version
    private Long version;

    @ApiModelProperty(example = "Jurassic World", required = true)
    private String title;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MovieDetail> movieDetails;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MovieGenre> movieGenres;
}
