package com.nostra.nostramovieapps.entity.person;

import com.nostra.nostramovieapps.entity.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "PERSON")
@Getter
@Setter
@NoArgsConstructor
public class Person extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;

    @Version
    private Long version;

    @ApiModelProperty(example = "Tom Cruise", required = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonDetail> personDetails;
}
