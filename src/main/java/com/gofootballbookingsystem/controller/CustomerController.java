package com.gofootballbookingsystem.controller;

import com.gofootballbookingsystem.dto.CustomerDTO;

import com.gofootballbookingsystem.exception.ResourceNotFoundException;
import com.gofootballbookingsystem.exception.UsernameAlreadyExistsException;
import com.gofootballbookingsystem.mapper.CustomerMapper;
import com.gofootballbookingsystem.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;





   /* @PostMapping("/createcustomer")
    public ResponseEntity<?>  createCustomer(@RequestBody CustomerDTO customerDTO) {

        try {
            if (customerService.isUsernameExists(customerDTO.getUserName()) ||
                    customerService.isEmailExists(customerDTO.getEmail())) {
                throw new UsernameAlreadyExistsException("Username or email already exists");
            }
            CustomerDTO customerDTO1=customerService.createCustomer(customerDTO);
            return ResponseEntity.ok(customerDTO1);
        }catch (UsernameAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username or email already exists");
        }
    }*/
    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/updatecustomer/{customer_id}")
    public ResponseEntity<?>  updateCustomer(@RequestBody CustomerDTO customerDTO,@PathVariable Long customer_id) {

        try {
            if (!customerService.isIdExists(customer_id)) {
                throw new ResourceNotFoundException("this user is not exists");
            }
            CustomerDTO customerDTO1=customerService.updateCustomer(customerDTO,customer_id);
            return ResponseEntity.ok(customerDTO1);
        }catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("this user is not exists");
        }


    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/deletecustomer/{customer_id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Long customer_id) {

        customerService.deleteCustomerById(customer_id);
        return new ResponseEntity<>("customer deleted succesfully.",HttpStatus.OK);
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/getcustomer/{customer_id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long customer_id) {

        CustomerDTO customerDTO=customerService.getCustomerById(customer_id);
        return new ResponseEntity<>(customerDTO,HttpStatus.OK);

    }


}
