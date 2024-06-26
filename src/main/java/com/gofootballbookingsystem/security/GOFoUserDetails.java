package com.gofootballbookingsystem.security;

import com.gofootballbookingsystem.entity.Customer;
import com.gofootballbookingsystem.entity.Owner;
import com.gofootballbookingsystem.repository.CustomerRepository;
import com.gofootballbookingsystem.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GOFoUserDetails implements UserDetailsService {


    @Autowired

    private  OwnerRepository ownerRepository;
    @Autowired

    private  CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameOremail) throws UsernameNotFoundException {

        Customer customer=customerRepository.findByUserNameOrEmail(usernameOremail,usernameOremail);
        Owner owner=ownerRepository.findByUserNameOrEmail(usernameOremail,usernameOremail);

        if (customer==null && owner !=null){

            Set<GrantedAuthority>authorities=owner.getRoles().stream()
                    .map((role)->new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toSet());
            return new User( usernameOremail, owner.getPassword(), authorities);
        }else if (owner==null && customer !=null){


            Set<GrantedAuthority>authorities=customer.getRoles().stream()
                    .map((role)->new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toSet());
            return new User( usernameOremail, customer.getPassword(), authorities);
        }else {
            throw new UsernameNotFoundException("this user "+usernameOremail+" not found");
        }

    }
}
