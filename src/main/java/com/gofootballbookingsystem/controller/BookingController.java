package com.gofootballbookingsystem.controller;

import com.gofootballbookingsystem.dto.BookingDetailsDTO;
import com.gofootballbookingsystem.dto.CustomerBookingDTO;
import com.gofootballbookingsystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private  BookingService bookingService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/createbooking")
    public ResponseEntity<?>createBooking(@RequestBody CustomerBookingDTO customerBookingDTO){
        if (customerBookingDTO.getHours() !=null ||customerBookingDTO.getDayOfWeek()!=null
                ||customerBookingDTO.getCustomerId()!=null ||customerBookingDTO.getPlaygroundId()!=null){
            BookingDetailsDTO bookingDetailsDTO=bookingService.createBooking(customerBookingDTO);
            return ResponseEntity.ok(bookingDetailsDTO);
        }else {
            return ResponseEntity.badRequest().body("field can not be null") ;
        }

    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/cancelBooking/{bookingId}")
    public ResponseEntity<?>cancelBooking(@PathVariable Long bookingId){
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok("canceled");
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/getAllCustomerBooking/{customerId}")
    public ResponseEntity<?>getAllCustomerBooking(@PathVariable Long customerId){
        List<BookingDetailsDTO> bookingDetailsDTOS=bookingService.getAllCustomerBookingById(customerId);

        if (bookingDetailsDTOS.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(bookingDetailsDTOS);

        }

    }
    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/getAllPlaygroundBooking/{playGroundId}")
    public ResponseEntity<?>getAllPlayGroundBooking(@PathVariable Long playGroundId){
        List<BookingDetailsDTO> bookingDetailsDTOS=bookingService.getAllPlaygroundBookingById(playGroundId);

        if (bookingDetailsDTOS.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(bookingDetailsDTOS);

        }

    }
}
