package com.gofootballbookingsystem.repository;

import com.gofootballbookingsystem.entity.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface BookingDetailRepository extends
        JpaRepository<BookingDetails,Long> {


    List<BookingDetails> findAllByCustomerId(Long customerId);
    List<BookingDetails> findAllByplayGroundId(Long playGroundId);
}
