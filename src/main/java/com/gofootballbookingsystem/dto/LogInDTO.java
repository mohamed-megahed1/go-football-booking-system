package com.gofootballbookingsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogInDTO {

    private String usernameOrEmail;
    private String password;
}
