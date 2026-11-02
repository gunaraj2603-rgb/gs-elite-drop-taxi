package com.gselite.gselite_taxi_backend.controller;

import com.gselite.gselite_taxi_backend.dto.BookingRequest;
import com.gselite.gselite_taxi_backend.entity.Booking;
import com.gselite.gselite_taxi_backend.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Booking> bookTaxi(@RequestBody BookingRequest request) {
        Booking savedBooking = bookingService.createBooking(request);
        return ResponseEntity.ok(savedBooking); // Returns the saved booking details with ID back to frontend
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }
}