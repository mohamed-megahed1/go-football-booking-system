package com.gofootballbookingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
public class CustomerBookingDTO {


    @NotBlank(message = "playgroundId can not be blank")

    private Long playgroundId;
    @NotBlank(message = "customerId can not be blank")

    private Long customerId;
    @NotBlank(message = "dayOfWeek can not be blank")

    private   DayOfWeek dayOfWeek;
    @NotBlank(message = "hours can not be blank")

    private   Set<LocalTime> hours;
}
