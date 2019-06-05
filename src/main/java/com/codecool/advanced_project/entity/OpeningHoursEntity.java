package com.codecool.advanced_project.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OpeningHoursEntity {

    @Id
    @GeneratedValue
    private Long id;

    /*
        Number of the day during the week
        Monday = 1 ... Sunday = 7
     */
    private Integer dayOfWeek;

    private LocalTime open;

    private LocalTime close;

    private LocalTime breakStart;

    private LocalTime breakEnd;

    @ManyToOne
    @Getter(AccessLevel.NONE)
    private ShopEntity shopEntity;

}
