package com.gofootballbookingsystem.mapper;


import com.gofootballbookingsystem.dto.PlayGroundDTO;
import com.gofootballbookingsystem.entity.PlayGround;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring",

nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)

public interface PlayGroundMapper {

    @Mappings({
       //   @Mapping(target = "ownerDTO",source = "owner"),
            @Mapping(target = "bookingDetailsDTOS",source = "bookingDetails"),
            @Mapping(target = "available_hoursDTO",source = "available_hours"),

    })

    PlayGroundDTO fromPlayGroundToPlayGroundDto(PlayGround playGround);

    @Mappings({
       //   @Mapping(target = "owner",source = "ownerDTO"),
            @Mapping(target = "bookingDetails",source = "bookingDetailsDTOS"),
            @Mapping(target = "available_hours",source = "available_hoursDTO")
    })
    PlayGround fromPlayGroundDtoToPlayGround(PlayGroundDTO playGroundDTO);


    @Mappings({
      //  @Mapping(target = "ownerDTO",source = "owner"),
            @Mapping(target = "bookingDetailsDTOS",source = "bookingDetails"),
            @Mapping(target = "available_hoursDTO",source = "available_hours")
    })
    List<PlayGroundDTO>fromPlayGroundToPlayGroundDtoList(List<PlayGround> playGrounds);

    @Mappings({
       //   @Mapping(target = "owner",source = "ownerDTO"),
            @Mapping(target = "bookingDetails",source = "bookingDetailsDTOS"),
            @Mapping(target = "available_hours",source = "available_hoursDTO")
    })
    List<PlayGround> fromPlayGroundDtoToPlayGroundList(List<PlayGroundDTO> playGroundDTO);

    @Mapping(target = "id", ignore = true) // Ignore mapping the id field
    PlayGround updatePlayGroundFromDTO(PlayGroundDTO playGroundDTO, @MappingTarget PlayGround playGround);

}
