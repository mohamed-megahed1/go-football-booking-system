package com.gofootballbookingsystem.mapper;

import com.gofootballbookingsystem.dto.BookingDetailsDTO;
import com.gofootballbookingsystem.entity.BookingDetails;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring",

        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)

public interface BookingDetailsMapper {



    BookingDetailsDTO fromBookingDetailsToBookingDetailsDto(BookingDetails bookingDetails);

    BookingDetails fromBookingDetailsDtoToBookingDetails(BookingDetailsDTO bookingDetailsDTO);


    List<BookingDetailsDTO> fromBookingDetailsToBookingDetailsDto(List<BookingDetails> bookingDetails);

    List<BookingDetails> fromBookingDetailsDtoToBookingDetails(List<BookingDetailsDTO> bookingDetailsDTO);
}
