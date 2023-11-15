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
@Table(name = "PERSON")
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
