package com.gofootballbookingsystem.service.Imp;

import com.gofootballbookingsystem.dto.CustomerDTO;
import com.gofootballbookingsystem.dto.PlayGroundDTO;
import com.gofootballbookingsystem.dto.PlaygroundDtoViewToCustomer;
import com.gofootballbookingsystem.entity.Customer;
import com.gofootballbookingsystem.entity.Owner;
import com.gofootballbookingsystem.entity.PlayGround;
import com.gofootballbookingsystem.exception.ResourceNotFoundException;
import com.gofootballbookingsystem.mapper.CustomerMapper;
import com.gofootballbookingsystem.mapper.PlayGroundDtoToViewCustomerMapper;
import com.gofootballbookingsystem.mapper.PlayGroundMapper;
import com.gofootballbookingsystem.repository.CustomerRepository;
import com.gofootballbookingsystem.repository.OwnerRepository;
import com.gofootballbookingsystem.repository.PlayGroundRepository;
import com.gofootballbookingsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor

public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public boolean isUsernameExists(String username) {
        return customerRepository.existsByUserName(username);
    }
    @Override
    public boolean isEmailExists(String email) {
        return customerRepository.existsByEmail(email);
    }
    @Override
    public boolean isIdExists(Long id) {
        return customerRepository.existsById(id);
    }

   /* @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {

        if (ownerRepository.existsByEmail(customerDTO.getEmail()) ||
                ownerRepository.existsByUserName(customerDTO.getUserName())){
            throw new RuntimeException("the email or user_name is already exists in the system");
        }else {
            Customer customer=customerMapper.fromCustomerDtoToCustomer(customerDTO);

            return customerMapper.fromCustomerToCustomerDto(customerRepository.save(customer)) ;

        }

    }*/

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, Long customer_id) {
        if (!customerDTO.getPassword().isEmpty() &&customerDTO.getPassword()!=null){
            customerDTO.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        }
        Customer customer= customerRepository.findById(customer_id)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found with id : "+customer_id));

        if (ownerRepository.existsByEmail(customerDTO.getEmail()) ||
                ownerRepository.existsByUserName(customerDTO.getUserName())){
            throw new RuntimeException("the email or user_name is already exists in the system");

        }else {
            Customer customer1 = customerMapper.updateCustomerFromDTO(customerDTO, customer);

            Customer updatedCustomer = customerRepository.save(customer1);

            return customerMapper.fromCustomerToCustomerDto(updatedCustomer);
        }
    }

    @Override
    public void deleteCustomerById(Long customer_id) {
        Customer customer= customerRepository.findById(customer_id)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found with id : "+customer_id));

        customerRepository.deleteById(customer_id);
    }

    @Override
    public CustomerDTO getCustomerById(Long customer_id) {

        Customer customer= customerRepository.findById(customer_id)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found with id : "+customer_id));

        return customerMapper.fromCustomerToCustomerDto(customer);
    }





}
