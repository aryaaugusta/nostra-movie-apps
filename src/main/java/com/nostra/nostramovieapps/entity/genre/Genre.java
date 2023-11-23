package com.nostra.nostramovieapps.entity.genre;

import com.nostra.nostramovieapps.entity.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity(name = "GENRE")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Genre extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;

    @Version
    private Long version;

    private String name;
}
