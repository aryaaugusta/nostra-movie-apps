package com.nostra.nostramovieapps.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "GENRE")
@Data
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(example = "Action", required = true)
    @Column(name = "NAME")
    private String name;

//    @Transient
//    @JsonManagedReference(value = "genreId")
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "genre", cascade = CascadeType.ALL)
//    @ApiModelProperty(hidden = true)
//    private List<MovieGenre> movieGenreList;
}
