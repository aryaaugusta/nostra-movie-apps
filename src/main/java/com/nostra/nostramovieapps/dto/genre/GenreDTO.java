package com.nostra.nostramovieapps.dto.genre;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nostra.nostramovieapps.dto.base.BaseDTO;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreDTO extends BaseDTO {
    private String name;
}
