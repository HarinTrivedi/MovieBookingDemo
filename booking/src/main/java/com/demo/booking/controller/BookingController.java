package com.demo.booking.controller;

import com.demo.booking.data.Booking;
import com.demo.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService service;
    private final ShowsClient showsClient;

    @PostMapping
    public Booking book(@RequestBody Booking booking) {
        ShowDTO show = showsClient.getShowById(booking.getShowId());

        if(show.getSeatsAvailablegetSeats() < booking.getSeats()) {
            return ResponseEntity.badRequest().body("Not enough seats available for booking").build();
        }

        // Save via Feign client OR Send message in RabbitMQ for oreder processing
        return service.saveBooking(booking, "BOOKED");
    }

    // can update booking with CANCELLED or FAILED

    @GetMapping("/user/{userId}")
    public List<Booking> getUserBookings(@PathVariable Long userId) {
        return service.getBookingsByUser(userId);
    }
}
