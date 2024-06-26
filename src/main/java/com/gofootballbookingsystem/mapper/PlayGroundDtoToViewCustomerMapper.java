package com.gofootballbookingsystem.mapper;


import com.gofootballbookingsystem.dto.PlayGroundDTO;
import com.gofootballbookingsystem.dto.PlaygroundDtoViewToCustomer;
import com.gofootballbookingsystem.entity.PlayGround;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",

nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)

public interface PlayGroundDtoToViewCustomerMapper {



    @Mapping(target = "availableHours",source = "available_hours")

    List<PlaygroundDtoViewToCustomer>fromPlayGroundToPlayGroundDtoList(List<PlayGround> playGrounds);

    @Mappings({
            @Mapping(target = "availableHours", source = "available_hours")
    })
    PlaygroundDtoViewToCustomer playGroundToPlaygroundDtoViewToCustomer(PlayGround playGround);


}
