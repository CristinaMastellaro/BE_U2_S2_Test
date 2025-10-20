package cristinamastellaro.BE_U2_S2_Test.services;

import cristinamastellaro.BE_U2_S2_Test.entities.Booking;
import cristinamastellaro.BE_U2_S2_Test.entities.Travel;
import cristinamastellaro.BE_U2_S2_Test.exceptions.NotFoundException;
import cristinamastellaro.BE_U2_S2_Test.payloads.BookingPayload;
import cristinamastellaro.BE_U2_S2_Test.repositories.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class BookingService {
    @Autowired
    private BookingRepository bRepo;
    @Autowired
    private TravelService tServ;

    public List<Booking> findAll() {
        return bRepo.findAll();
    }

    public Booking saveBooking(BookingPayload bP) {
        // Non ci sono particolari controlli da effettuare
        Travel travel = tServ.findTravelById(bP.travelId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(bP.dateOfRequest(), formatter);
        Booking booking = new Booking(date, travel, bP.notes());
        bRepo.save(booking);
        log.info("The travel to " + travel.getDestination() + " has been successfully booked!");
        return booking;
    }

    public Booking findBookingById(UUID id) {
        return bRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Booking updateBookingById(UUID id, BookingPayload newInfo) {
        Booking bookingToUpdate = findBookingById(id);
        Travel newTravel = tServ.findTravelById(newInfo.travelId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(newInfo.dateOfRequest(), formatter);

        bookingToUpdate.setDateOfRequest(date);
        bookingToUpdate.setTravel(newTravel);
        bookingToUpdate.setPreferencesOrNotes(newInfo.notes());

        bRepo.save(bookingToUpdate);
        log.info("The booking with id " + id + " has been successfully updated!");
        return bookingToUpdate;
    }

    public void deleteBooking(UUID id) {
        Booking bookingToDelete = findBookingById(id);
        bRepo.delete(bookingToDelete);
        log.info("The booking has been successfully deleted");
    }
}
