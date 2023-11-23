package com.nostra.nostramovieapps.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Builder
public class DataSorting {
    private String field;
    private SortOption sort;
}
