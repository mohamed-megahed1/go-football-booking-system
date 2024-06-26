package com.gofootballbookingsystem.service.Imp;


import com.gofootballbookingsystem.dto.BookingDetailsDTO;
import com.gofootballbookingsystem.dto.CustomerBookingDTO;
import com.gofootballbookingsystem.entity.BookingDetails;
import com.gofootballbookingsystem.entity.Customer;
import com.gofootballbookingsystem.entity.Owner;
import com.gofootballbookingsystem.entity.PlayGround;
import com.gofootballbookingsystem.exception.ResourceNotFoundException;
import com.gofootballbookingsystem.mapper.BookingDetailsMapper;
import com.gofootballbookingsystem.repository.BookingDetailRepository;
import com.gofootballbookingsystem.repository.CustomerRepository;
import com.gofootballbookingsystem.repository.OwnerRepository;
import com.gofootballbookingsystem.repository.PlayGroundRepository;
import com.gofootballbookingsystem.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor

public class BookingServiceImp implements BookingService {


    private final CustomerRepository customerRepository;
    private final OwnerRepository ownerRepository;
    private final PlayGroundRepository playGroundRepository;
    private final BookingDetailRepository bookingDetailRepository;
    private final BookingDetailsMapper bookingDetailsMapper;


    @Override
    public BookingDetailsDTO createBooking(CustomerBookingDTO customerBookingDTO) {
        Customer customer=customerRepository.findById(customerBookingDTO.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("customer not found"));
        PlayGround playGround=playGroundRepository.findById(customerBookingDTO.getPlaygroundId()).orElseThrow(() -> new ResourceNotFoundException("playground not found"));
        Map<DayOfWeek, Set<LocalTime>> available_hours=playGround.getAvailable_hours();
        BookingDetails bookingDetails=new BookingDetails();
        if (available_hours.get(customerBookingDTO.getDayOfWeek())!=null){

            if (available_hours.get(customerBookingDTO.getDayOfWeek()).containsAll(customerBookingDTO.getHours())){
                double totalPrice=playGround.getPrice()*customerBookingDTO.getHours().size();
                if (totalPrice<=customer.getBalance()){
                    customer.setBalance(customer.getBalance()-totalPrice);
                    Owner owner= ownerRepository.findById( playGround.getOwnerId()).orElseThrow(RuntimeException::new);
                    owner.setBending(owner.getBending()+totalPrice);
                    playGround.getAvailable_hours().computeIfAbsent(customerBookingDTO.getDayOfWeek(),day -> new HashSet<>()).removeAll(customerBookingDTO.getHours());
                    ///////////////
                    Map<DayOfWeek, Set<LocalTime>> bookingHours = new HashMap<>();
                    bookingHours.put(customerBookingDTO.getDayOfWeek(),customerBookingDTO.getHours());
                    /////////////
                    bookingDetails.setCreatedDate(LocalDateTime.now());
                    bookingDetails.setExpiredDate(calcExpireDate(customerBookingDTO.getDayOfWeek(),customerBookingDTO.getHours()));
                    bookingDetails.setCustomerName(customer.getFirstName()+" "+customer.getLastName());
                    bookingDetails.setPlaygroundName(playGround.getName());
                    bookingDetails.setBooking_hours(bookingHours);
                    bookingDetails.setTotalHours(customerBookingDTO.getHours().size());
                    bookingDetails.setTotalPrice(totalPrice);
                    bookingDetails.setCustomerId(customer.getId());
                    bookingDetails.setPlayGroundId(playGround.getId());
                    /////////////////////////////////////////////
                    customer.addBookingDetails(bookingDetails);
                    playGround.addBookingDetails(bookingDetails);
                    ////////////////////////////////////////////
                    bookingDetailRepository.save(bookingDetails);
                    customerRepository.save(customer);
                    playGroundRepository.save(playGround);
                    ownerRepository.save(owner);
                }else {
                    throw new RuntimeException("not enough balance ");
                }

            }else {
                throw new RuntimeException("playground not available on this hours ");
            }
        }
        return bookingDetailsMapper.fromBookingDetailsToBookingDetailsDto(bookingDetails);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        BookingDetails bookingDetails=bookingDetailRepository.findById(bookingId).orElseThrow(()
                -> new RuntimeException("booking not found"));

        //user can cancel and refund all money before 12 hour of expiration
        if (bookingDetails.getExpiredDate().minusHours(12).isAfter(LocalDateTime.now())){
            Customer customer=customerRepository.findById(bookingDetails.getCustomerId()).orElseThrow(() ->
                    new RuntimeException("customer not found"));
            PlayGround playGround=playGroundRepository.findById(bookingDetails.getPlayGroundId()).orElseThrow(() ->
                    new RuntimeException("playground not found"));
            Owner owner=ownerRepository.findById(playGround.getOwnerId()).orElseThrow(() ->
                    new RuntimeException("owner not found"));
            ////////////////////return money to customer//////////////////////////
            customer.setBalance(customer.getBalance()+bookingDetails.getTotalPrice());
            owner.setBending(owner.getBending()-bookingDetails.getTotalPrice());
            //////////////////////update available hours////////////////////////////
            Map<DayOfWeek,Set<LocalTime>> bookingHours = bookingDetails.getBooking_hours();
            Map<DayOfWeek,Set<LocalTime>> availableHours = playGround.getAvailable_hours();
            DayOfWeek dayOfWeek=bookingDetails.getExpiredDate().getDayOfWeek();
            availableHours.get(dayOfWeek).addAll(bookingHours.get(dayOfWeek));
            playGround.setAvailable_hours(availableHours);
            /////////////////////sava all changes/////////////////////////
            customerRepository.save(customer);
            ownerRepository.save(owner);
            playGroundRepository.save(playGround);
            //////////////////////remove booking////////////////////////
            bookingDetailRepository.deleteById(bookingId);
        }else {
            throw new RuntimeException("booking was already canceled");
        }


    }

    @Override
    public List<BookingDetailsDTO> getAllCustomerBookingById(Long customerId) {

        List<BookingDetails> bookingDetails=bookingDetailRepository.findAllByCustomerId(customerId);

        return bookingDetailsMapper.fromBookingDetailsToBookingDetailsDto(bookingDetails);
    }

    @Override
    public List<BookingDetailsDTO> getAllPlaygroundBookingById(Long playGroundId) {

        List<BookingDetails> bookingDetails=bookingDetailRepository.findAllByplayGroundId(playGroundId);

        return bookingDetailsMapper.fromBookingDetailsToBookingDetailsDto(bookingDetails);
    }


    private LocalDateTime calcExpireDate(DayOfWeek  dayOfWeek,Set<LocalTime> hours){

        LocalDateTime nextday = LocalDateTime.now().with(TemporalAdjusters.next(dayOfWeek));

        LocalTime lastHour=Collections.max(hours);

        LocalDateTime expireTime=nextday.withHour(lastHour.getHour()).withMinute(lastHour.getMinute());
        return expireTime;
    }
}
