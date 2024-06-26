package com.gofootballbookingsystem.base;

import com.gofootballbookingsystem.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public  class BaseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,name = "user_name")
    @NotBlank(message = "userName cannot be blank")
    private String userName;
    @Column(name = "first_name")
    @NotBlank(message = "firstName cannot be blank")
    private String firstName;
    @Column(name = "last_name")
    @NotBlank(message = "lastName cannot be blank")
    private String lastName;
    @Column(unique = true)
    @NotBlank(message = "email cannot be blank")
    @Email
    private String email;
    @NotBlank(message = "password cannot be blank")
    private String password;
    @NotBlank(message = "Street address is required")
    private String streetAddress;
    @NotBlank(message = "Neighborhood is required")
    private String neighborhood;
    @NotBlank(message = "City is required")
    private String city;


    private Double balance=0.0;


}
