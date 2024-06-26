package com.gofootballbookingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="play_ground")

public class PlayGround {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotBlank(message = "playground name cannot be blank")
    private String name;
    @NotNull(message = "price cannot be blank")
    @Positive
    private Double price;
    @Column(name = "team_capacity")
    @NotNull(message = "teamCapacity cannot be blank")
    private Integer teamCapacity;
    @NotBlank(message = "Street address is required")
    private String streetAddress;
    @NotBlank(message = "Neighborhood is required")
    private String neighborhood;
    @NotBlank(message = "City is required")
    private String city;
    @OneToMany
    @JoinColumn(name = "playground_id")
    private Set<BookingDetails>bookingDetails;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "playground_hours", joinColumns = @JoinColumn(name = "playground_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @NotNull(message = "available_hours cannot be blank")
    private Map<DayOfWeek, Set<LocalTime>> available_hours = new HashMap<>();

    @Column(name = "owner_id")
    private Long ownerId;

    public void addBookingDetails(BookingDetails bookingDetails){
        this.bookingDetails.add(bookingDetails);
    }
}
