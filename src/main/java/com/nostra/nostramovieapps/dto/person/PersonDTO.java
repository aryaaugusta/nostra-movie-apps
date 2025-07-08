package com.nostra.nostramovieapps.dto.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nostra.nostramovieapps.dto.base.BaseDTO;
import com.nostra.nostramovieapps.dto.movie.MovieDetailDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
public class PersonDTO extends BaseDTO {
    private String name;
    private Set<PersonDetailDTO> personDetails;
}
