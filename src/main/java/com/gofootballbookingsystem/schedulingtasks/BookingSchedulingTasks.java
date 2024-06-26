package com.gofootballbookingsystem.schedulingtasks;


import com.gofootballbookingsystem.entity.BookingDetails;
import com.gofootballbookingsystem.entity.Owner;
import com.gofootballbookingsystem.entity.PlayGround;
import com.gofootballbookingsystem.repository.BookingDetailRepository;
import com.gofootballbookingsystem.repository.OwnerRepository;
import com.gofootballbookingsystem.repository.PlayGroundRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@AllArgsConstructor
public class BookingSchedulingTasks {

    private static final Logger log = LoggerFactory.getLogger(BookingSchedulingTasks.class);

   // private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private final BookingDetailRepository bookingDetailRepository;
    @Autowired
    private final OwnerRepository ownerRepository;
    @Autowired
    private final PlayGroundRepository playGroundRepository;




    @Scheduled( cron = "0 0 1 * * *")
    public void reportCurrentTime() {

       List<BookingDetails>bookingDetails= bookingDetailRepository.findAll();

       if (!bookingDetails.isEmpty()){
           for (BookingDetails booking: bookingDetails) {


               if (booking.getExpiredDate().isBefore(LocalDateTime.now())){

                   PlayGround playGround=playGroundRepository.findById(booking.getPlayGroundId()).orElseThrow(
                           () -> new RuntimeException()
                   );
                   Owner owner=ownerRepository.findById(playGround.getOwnerId()).orElseThrow(
                           () -> new RuntimeException()
                   );

                   Double totalPrice=booking.getTotalPrice();
                   if (totalPrice<owner.getBending()){
                       owner.setBending(owner.getBending()-totalPrice);
                       owner.setBalance(owner.getBalance()+totalPrice);
                       ownerRepository.save(owner);
                   }

                   /////////update playgrounds available hours/////////////////
                   Map<DayOfWeek, Set<LocalTime>> bookingHours = booking.getBooking_hours();
                   Map<DayOfWeek,Set<LocalTime>> availableHours = playGround.getAvailable_hours();
                   DayOfWeek dayOfWeek=booking.getExpiredDate().getDayOfWeek();
                   availableHours.get(dayOfWeek).addAll(bookingHours.get(dayOfWeek));
                   playGround.setAvailable_hours(availableHours);
                   //////////////////////////////////////////
                   playGroundRepository.save(playGround);
                   bookingDetailRepository.delete(booking);
                   log.info("Owner {} has recived {} from booking {} of playground {} in time {} ",
                           owner.getId(),totalPrice,booking.getId(),playGround.getName(),LocalDateTime.now());

               }
           }
       }


    }


}
