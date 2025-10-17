package cristinamastellaro.BE_U2_S2_Test.controllers;

import cristinamastellaro.BE_U2_S2_Test.entities.Booking;
import cristinamastellaro.BE_U2_S2_Test.exceptions.PayloadValidationException;
import cristinamastellaro.BE_U2_S2_Test.payloads.BookingPayload;
import cristinamastellaro.BE_U2_S2_Test.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bServ;

    @GetMapping
    public List<Booking> findAll() {
        return bServ.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(@RequestBody @Validated BookingPayload bP, BindingResult validation) {
        if (validation.hasErrors())
            throw new PayloadValidationException(validation.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList());
        return bServ.saveBooking(bP);
    }

    @GetMapping("/{bookingId}")
    public Booking findBookingById(@PathVariable UUID bookingId) {
        return bServ.findBookingById(bookingId);
    }

    @PutMapping("/{bookingId}")
    public Booking updateBookingById(@PathVariable UUID bookingId, @RequestBody @Validated BookingPayload bP, BindingResult validation) {
        if (validation.hasErrors())
            throw new PayloadValidationException(validation.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList());
        return bServ.updateBookingById(bookingId, bP);
    }

    @DeleteMapping("/{bookingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable UUID bookingId) {
        bServ.deleteBooking(bookingId);
    }
}

