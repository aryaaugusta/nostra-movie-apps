package com.nostra.nostramovieapps.dto.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nostra.nostramovieapps.entity.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public class BaseDTO {
    private Long id;
    private Long version;
    private Status status;
    private String createdBy;
    private ZonedDateTime createdAt;
    private String updatedBy;
    private ZonedDateTime updatedAt;
}
