package com.nostra.nostramovieapps.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class SearchResult<R> {

    private List<R> rows = new ArrayList<>();

    private long totalRows;
    private long totalPages;
    private int currentPageNumber;
    private int currentPageSize;
}
