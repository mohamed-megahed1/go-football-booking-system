package com.gofootballbookingsystem.dto;


import com.gofootballbookingsystem.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

public class RegisterDto {

    @NotBlank
    private String userName;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String streetAddress;
    @NotBlank
    private String neighborhood;
    @NotBlank
    private String city;

    @NotBlank
    private String role;
}
