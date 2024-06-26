package com.gofootballbookingsystem.dto;

import com.gofootballbookingsystem.base.BaseUserDTO;
import com.gofootballbookingsystem.entity.PlayGround;
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
public class OwnerDTO extends BaseUserDTO {



    private Set<PlayGround> playGroundDTOS;
    private Double balance;
    private Double bending;


}
