package com.gofootballbookingsystem.entity;

import com.gofootballbookingsystem.base.BaseUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@DiscriminatorValue("customer")
public class Customer extends BaseUser {


    @OneToMany
    @JoinColumn(name = "customer_id")
    private Set<BookingDetails> bookingDetails;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "customer_roles")
    private Set<Role> roles=new HashSet<>();


    public void addBookingDetails(BookingDetails bookingDetails){
        this.bookingDetails.add(bookingDetails);
    }

}
