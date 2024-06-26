package com.gofootballbookingsystem.controller;


import com.gofootballbookingsystem.base.BaseUser;
import com.gofootballbookingsystem.base.UserBaseRepository;
import com.gofootballbookingsystem.dto.LogInDTO;
import com.gofootballbookingsystem.dto.RegisterDto;
import com.gofootballbookingsystem.security.JwtAuthResponse;
import com.gofootballbookingsystem.service.AuthService;
import com.gofootballbookingsystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;



    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){

        String response=authService.register(registerDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> logIn(@RequestBody LogInDTO logInDTO){

        String toekn=authService.logIn(logInDTO);

        JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(toekn);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }


}
