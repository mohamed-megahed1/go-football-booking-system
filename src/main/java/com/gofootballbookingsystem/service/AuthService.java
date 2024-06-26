package com.gofootballbookingsystem.service;

import com.gofootballbookingsystem.dto.LogInDTO;
import com.gofootballbookingsystem.dto.RegisterDto;

public interface AuthService {

     String register(RegisterDto registerDto);
     String logIn(LogInDTO logInDTO);
}
