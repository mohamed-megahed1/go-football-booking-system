package com.gofootballbookingsystem.mapper;

import com.gofootballbookingsystem.dto.OwnerDTO;
import com.gofootballbookingsystem.entity.Owner;
import org.mapstruct.*;


@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)

public interface OwnerMapper {

    @Mappings({
            @Mapping(target = "playGroundDTOS",source = "playGround")
         //   @Mapping(target = "password",ignore = true)
    })
    OwnerDTO fromOwnerToOwnerDto(Owner owner);
    @Mappings({

            @Mapping(target = "playGround",source = "playGroundDTOS")
         //   @Mapping(target = "password",ignore = true)
    })
    Owner fromOwnerDtoToOwner(OwnerDTO ownerDTO);

    @Mapping(target = "id", ignore = true) // Ignore mapping the id field
    Owner updateOwnerFromDTO(OwnerDTO ownerDTO, @MappingTarget Owner owner);

}
