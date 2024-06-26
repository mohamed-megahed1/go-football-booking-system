package com.gofootballbookingsystem.service.Imp;


import com.gofootballbookingsystem.dto.LogInDTO;
import com.gofootballbookingsystem.dto.RegisterDto;
import com.gofootballbookingsystem.entity.Customer;
import com.gofootballbookingsystem.entity.Owner;
import com.gofootballbookingsystem.entity.Role;

import com.gofootballbookingsystem.repository.CustomerRepository;
import com.gofootballbookingsystem.repository.OwnerRepository;
import com.gofootballbookingsystem.repository.RoleRepository;
import com.gofootballbookingsystem.security.JwtTokenProvider;
import com.gofootballbookingsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthServiceImp implements AuthService {

    private final CustomerRepository customerRepository;
    private final OwnerRepository ownerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDto registerDto) {

        if (customerRepository.existsByUserName(registerDto.getUserName())||
                customerRepository.existsByEmail(registerDto.getEmail())||
                ownerRepository.existsByUserName(registerDto.getUserName())||
                ownerRepository.existsByEmail(registerDto.getEmail())){
            throw new RuntimeException("the email or user_name is already exists in the system");
        }else {

           if (registerDto.getRole().toString().toUpperCase().equals("CUSTOMER")){
               Customer customer=new Customer();
               customer.setUserName(registerDto.getUserName());
               customer.setFirstName(registerDto.getFirstName());
               customer.setLastName(registerDto.getLastName());
               customer.setEmail(registerDto.getEmail());
               customer.setStreetAddress(registerDto.getStreetAddress());
               customer.setNeighborhood(registerDto.getNeighborhood());
               customer.setCity(registerDto.getCity());
               customer.setPassword(passwordEncoder.encode(registerDto.getPassword()));

               Set<Role> roles=new HashSet<>();
               Role userRole= roleRepository.findByName("ROLE_CUSTOMER");
               roles.add(userRole);

               customer.setRoles(roles);
               customerRepository.save(customer);
               return "User Registered  Successfully";

           }else if (registerDto.getRole().toString().toUpperCase().equals("OWNER")){
               Owner owner=new Owner();
               owner.setUserName(registerDto.getUserName());
               owner.setFirstName(registerDto.getFirstName());
               owner.setLastName(registerDto.getLastName());
               owner.setEmail(registerDto.getEmail());
               owner.setStreetAddress(registerDto.getStreetAddress());
               owner.setNeighborhood(registerDto.getNeighborhood());
               owner.setCity(registerDto.getCity());
               owner.setPassword(passwordEncoder.encode(registerDto.getPassword()));

               Set<Role> roles=new HashSet<>();
               Role userRole=  roleRepository.findByName("ROLE_OWNER");
               roles.add(userRole);

               owner.setRoles(roles);
               ownerRepository.save(owner);

               return "User Registered  Successfully";
           }else {
               throw new RuntimeException("Role uncleared");
           }

        }


    }

    @Override
    public String logIn(LogInDTO logInDTO) {
    Authentication authentication= authenticationManager.
            authenticate(new UsernamePasswordAuthenticationToken(
                logInDTO.getUsernameOrEmail(),
                logInDTO.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtTokenProvider.generateToken(authentication);
        return token;
    }


}
