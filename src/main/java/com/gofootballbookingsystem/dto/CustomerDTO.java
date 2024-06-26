package com.gofootballbookingsystem.dto;

import com.gofootballbookingsystem.base.BaseUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO extends BaseUserDTO{


    private Set<BookingDetailsDTO> bookingDetailsDTOS;

    private Double balance;


    public void addBookingDetails(BookingDetailsDTO bookingDetailsDTO){
        this.bookingDetailsDTOS.add(bookingDetailsDTO);
    }
}
