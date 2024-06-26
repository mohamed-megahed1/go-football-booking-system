package com.gofootballbookingsystem.dto;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetailsDTO {



    private Long id;
    private Long playGroundId;
    private Long customerId;
    private LocalDateTime createdDate;
    private LocalDateTime expiredDate;
    private String customerName;
    private String playgroundName;
    private Double totalPrice;
    private Integer totalHours;
    private Map<DayOfWeek, Set<LocalTime>> booking_hours = new HashMap<>();

}
