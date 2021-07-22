package com.nostra.nostramovieapps.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Column(name = "STATUS")
    private Boolean status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", updatable = false)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED_DATE", insertable = false)
    private Date modifiedDate;

    @Column(name = "CREATED_BY", length = 36)
    private String createdBy;

    @Column(name = "MODIFIED_BY", length = 36)
    private String modifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE", insertable = false)
    private Date endDate;

}
