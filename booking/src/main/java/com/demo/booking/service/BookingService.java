package com.demo.booking.service;

import com.demo.booking.data.Booking;
import com.demo.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository repository;

    @Tras
    public Booking saveBooking(Booking booking, String status) {
        booking.setStatus(status);
        booking.setBookingTime(LocalDateTime.now());
        return repository.save(booking);
    }

    public List<Booking> getBookingsByUser(Long userId) {
        return repository.findByUserId(userId)
    }

}
