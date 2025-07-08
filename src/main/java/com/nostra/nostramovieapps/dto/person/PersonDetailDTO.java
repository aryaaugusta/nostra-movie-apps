package com.nostra.nostramovieapps.dto.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nostra.nostramovieapps.dto.base.BaseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
public class PersonDetailDTO extends BaseDTO {
    private String biography;
    private String birthday;
    private String knownForDepartment;
    private String placeOfBirth;
    private Double popularity;
    private String profilePath;
}
