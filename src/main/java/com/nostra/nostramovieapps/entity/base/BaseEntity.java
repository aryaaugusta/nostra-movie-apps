package com.nostra.nostramovieapps.entity.base;

import com.nostra.nostramovieapps.entity.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.ZonedDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
public class BaseEntity implements Serializable {

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(updatable = false)
    private String createdBy;

    @Column(name = "CREATED_AT", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    private ZonedDateTime createdAt;

    private String updatedBy;

    @Column(name = "UPDATED_AT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime updatedAt;
}
