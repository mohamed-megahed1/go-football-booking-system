package com.gofootballbookingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaygroundDtoViewToCustomer {
    private Long id;
    private String name;
    private Double price;
    private Integer teamCapacity;
    private String streetAddress;
    private String neighborhood;
    private String city;
    private Map<DayOfWeek, Set<LocalTime>> availableHours;
}
