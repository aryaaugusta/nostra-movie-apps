package com.nostra.nostramovieapps.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nostra.nostramovieapps.entity.enums.QueryOperator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Builder
public class Filter {
    private String field;
    private QueryOperator operator;
    private String value;
}
