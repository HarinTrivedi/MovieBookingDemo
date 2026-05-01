package com.demo.booking.controller;

import com.demo.booking.controller.dto.ShowDTO;
import com.demo.booking.data.Booking;
import com.demo.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService service;
    private final ShowsClient showsClient;

    @PostMapping
    public ResponseEntity<Booking> book(@RequestBody Booking booking) {
        ShowDTO show = showsClient.getShowById(booking.getShowId());

        // We can have custom exception for general error message format
        if(show.getSeatsAvailable() < booking.getSeats()) {
            var obj = new Booking();
            obj.setStatus("Not enough seats available for booking");
            return ResponseEntity.badRequest().body(obj);
        }

        // TODO Save via Feign client OR Send message in RabbitMQ for oreder processing
        return ResponseEntity.ok(service.saveBooking(booking, "BOOKED"));
    }

    // can update booking with CANCELLED or FAILED

    @GetMapping("/user/{userId}")
    public List<Booking> getUserBookings(@PathVariable Long userId) {
        return service.getBookingsByUser(userId);
    }
}
