package com.gofootballbookingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlayGroundDTO {

    private Long id;


    private String name;


    private Double price;


    private Integer teamCapacity;

    private String streetAddress;
    private String neighborhood;
    private String city;


    private Set<BookingDetailsDTO> bookingDetailsDTOS;


    private Map<DayOfWeek, Set<LocalTime>> available_hoursDTO = new HashMap<>();


    private Long ownerId;


}
