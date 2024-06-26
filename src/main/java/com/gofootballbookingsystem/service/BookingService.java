package com.gofootballbookingsystem.service;

import com.gofootballbookingsystem.dto.BookingDetailsDTO;
import com.gofootballbookingsystem.dto.CustomerBookingDTO;

import java.util.List;

public interface BookingService {


    BookingDetailsDTO createBooking(CustomerBookingDTO customerBookingDTO);

    void cancelBooking(Long bookingId);

    List<BookingDetailsDTO>getAllCustomerBookingById(Long CustomerId);

    List<BookingDetailsDTO>getAllPlaygroundBookingById(Long playGroundId);



}
