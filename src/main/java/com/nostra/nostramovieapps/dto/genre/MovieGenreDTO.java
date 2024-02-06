package com.nostra.nostramovieapps.dto.genre;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class MovieGenreDTO {
    private Long id;
    private GenreDTO genre;
    private Long genreId;
    private String name;
}
