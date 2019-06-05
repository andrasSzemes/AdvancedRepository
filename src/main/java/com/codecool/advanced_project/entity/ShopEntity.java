package com.codecool.advanced_project.entity;

import com.codecool.advanced_project.exception.NoOpeningHoursException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ShopEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String address;

    @OneToMany(mappedBy = "shopEntity")
    private List<OpeningHoursEntity> openingHours;

    @Transient
    private Boolean open;

    @ElementCollection
    private List<CustomTagEntity> tags;

    public void setIsOpen() throws NoOpeningHoursException {
        LocalDate actualDate = LocalDate.now();
        LocalTime actualTime = LocalTime.now();
        int numOfDay = actualDate.getDayOfWeek().getValue();
        OpeningHoursEntity openingHoursOfToday = openingHours.stream()
                .filter(element -> element.getDayOfWeek() == numOfDay)
                .findFirst()
                .orElseThrow(NoOpeningHoursException::new);
        open = actualTime.isAfter(openingHoursOfToday.getOpen()) && actualTime.isBefore(openingHoursOfToday.getClose());

    }

}
