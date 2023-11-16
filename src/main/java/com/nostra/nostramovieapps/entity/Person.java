package com.nostra.nostramovieapps.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "PERSON")
@Data
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(example = "Colin Trevorrow", required = true)
    @Column(name = "NAME")
    private String name;

//    @Transient
//    @JsonManagedReference(value = "personId")
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person", cascade = CascadeType.ALL)
//    @ApiModelProperty(hidden = true)
//    private List<MovieCrew> movieCrewList;
}
