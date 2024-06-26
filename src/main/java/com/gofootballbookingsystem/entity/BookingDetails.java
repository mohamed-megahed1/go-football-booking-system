
package com.gofootballbookingsystem.entity;
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


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking_details")
public class BookingDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;
    @Column(name = "expired_date")
    private LocalDateTime expiredDate;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "playground_name")
    private String playgroundName;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "total_hours")
    private Integer totalHours;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "booking_hours", joinColumns = @JoinColumn(name = "booking_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @NotNull(message = "booking_hours cannot be blank")
    private Map<DayOfWeek, Set<LocalTime>> booking_hours = new HashMap<>();

    @Column(name = "playground_id")
    private Long playGroundId;

    @Column(name = "customer_id")
    private Long customerId;


}
