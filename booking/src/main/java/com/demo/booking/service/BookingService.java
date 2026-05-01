package com.demo.booking.service;

import com.demo.booking.data.Booking;
import com.demo.booking.repository.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository repository;

    @Transactional
    public Booking saveBooking(Booking booking, String status) {
        booking.setStatus(status);
        booking.setBookingTime(LocalDateTime.now());
        return repository.save(booking);
    }

    public List<Booking> getBookingsByUser(Long userId) {
        return repository.findByUserId(userId);
    }

}
