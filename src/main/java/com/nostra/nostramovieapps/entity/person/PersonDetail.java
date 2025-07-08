package com.nostra.nostramovieapps.entity.person;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "PERSON_DET")
@Getter
@Setter
@NoArgsConstructor
public class PersonDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(example = "Thomas Cruise Mapother IV (born July 3, 1962), known professionally as Tom Cruise, " +
            "is an American actor and producer. One of the world's highest-paid actors, he has received various accolades, " +
            "including an Honorary Palme d'Or and three Golden Globe Awards, in addition to nominations for three Academy Awards. " +
            "His films have grossed over $4 billion in North America and over $11.1 billion worldwide, " +
            "making him one of the highest-grossing box office stars of all time.")
    private String biography;

    @ApiModelProperty(example = "1962-07-03")
    private String birthday;

    @ApiModelProperty(example = "Acting")
    private String knownForDepartment;

    @ApiModelProperty(example = "Syracuse, New York, USA")
    private String placeOfBirth;

    @ApiModelProperty(example = "43.061")
    private Double popularity;

    @ApiModelProperty(example = "/eOh4ubpOm2Igdg0QH2ghj0mFtC.jpg")
    private String profilePath;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person person;
}
