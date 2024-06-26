package com.gofootballbookingsystem.mapper;

import com.gofootballbookingsystem.dto.CustomerDTO;
import com.gofootballbookingsystem.dto.OwnerDTO;
import com.gofootballbookingsystem.entity.Customer;
import com.gofootballbookingsystem.entity.Owner;
import org.mapstruct.*;


@Mapper(componentModel = "spring",

        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)

public interface CustomerMapper {


    @Mapping(target = "bookingDetailsDTOS",source = "bookingDetails")
    CustomerDTO fromCustomerToCustomerDto(Customer customer);
    @Mapping(target = "bookingDetails",source = "bookingDetailsDTOS")
    Customer fromCustomerDtoToCustomer(CustomerDTO customerDTO);
    @Mapping(target = "id", ignore = true) // Ignore mapping the id field
    Customer updateCustomerFromDTO(CustomerDTO customerDTO, @MappingTarget Customer customer);

}
