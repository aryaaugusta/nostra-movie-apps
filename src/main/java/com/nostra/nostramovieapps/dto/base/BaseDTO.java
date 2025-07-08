package com.nostra.nostramovieapps.dto.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nostra.nostramovieapps.entity.enums.Status;
import lombok.*;

import java.time.ZonedDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDTO {
    private Long id;
    private Long version;
    private Status status;
    private String createdBy;
    private ZonedDateTime createdAt;
    private String updatedBy;
    private ZonedDateTime updatedAt;
}
