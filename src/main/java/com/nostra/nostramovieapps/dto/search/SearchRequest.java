package com.nostra.nostramovieapps.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class SearchRequest {
    private List<Filter> filters;
    private List<DataSorting> sorts;
    private int page;
    private int limit = 20;
}
