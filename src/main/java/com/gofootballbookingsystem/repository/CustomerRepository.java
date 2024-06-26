package com.gofootballbookingsystem.repository;

import com.gofootballbookingsystem.base.UserBaseRepository;
import com.gofootballbookingsystem.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends UserBaseRepository<Customer> {
   Customer findByUserNameOrEmail(String userName, String email);

}
