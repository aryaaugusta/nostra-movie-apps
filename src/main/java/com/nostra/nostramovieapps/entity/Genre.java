package com.nostra.nostramovieapps.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "GENRE")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(example = "Action", required = true)
    @Column(name = "NAME")
    private String name;

    @Transient
    @JsonManagedReference(value = "genreId")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "genre", cascade = CascadeType.ALL)
    private List<MovieGenre> movieGenreList;
}
