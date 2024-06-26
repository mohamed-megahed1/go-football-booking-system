package com.gofootballbookingsystem.base;

import com.gofootballbookingsystem.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public  class BaseUserDTO {


    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String streetAddress;
    private String neighborhood;
    private String city;
    private Double balance=0.0;
    private Set<Role> roles;

}
