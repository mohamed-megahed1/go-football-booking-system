package com.gofootballbookingsystem.service;

import com.gofootballbookingsystem.base.UserServiceBase;
import com.gofootballbookingsystem.dto.CustomerDTO;


public interface CustomerService extends UserServiceBase {

  //  CustomerDTO createCustomer(CustomerDTO  customerDTO);

    CustomerDTO updateCustomer(CustomerDTO  customerDTO,Long customer_id);

    void deleteCustomerById(Long customer_id);

    CustomerDTO getCustomerById(Long customer_id);




}
