package com.codecool.advanced_project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ElementCollection
    private List<LocalTime> monday;

    @ElementCollection
    private List<LocalTime> tuesday;

    @ElementCollection
    private List<LocalTime> wednesday;

    @ElementCollection
    private List<LocalTime> thursday;

    @ElementCollection
    private List<LocalTime> friday;

    @ElementCollection
    private List<LocalTime> saturday;

    @ElementCollection
    private List<LocalTime> sunday;

}
