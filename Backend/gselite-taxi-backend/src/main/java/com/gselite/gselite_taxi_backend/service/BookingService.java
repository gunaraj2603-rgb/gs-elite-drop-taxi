package com.gselite.gselite_taxi_backend.service;

import com.gselite.gselite_taxi_backend.dto.BookingRequest;
import com.gselite.gselite_taxi_backend.entity.Booking;
import com.gselite.gselite_taxi_backend.repository.BookingRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final JavaMailSender mailSender;

    public BookingService(BookingRepository bookingRepository, JavaMailSender mailSender) {
        this.bookingRepository = bookingRepository;
        this.mailSender = mailSender;
    }

    public Booking createBooking(BookingRequest request) {
        // 1. Save booking into database using standard setter methods (safer and avoids constructor mismatch)
        Booking booking = new Booking();
        booking.setName(request.getName());
        booking.setMobile(request.getMobile());
        booking.setPickup(request.getPickup());
        booking.setDropOff(request.getDropOff());
        booking.setTripType(request.getTripType());
        booking.setDate(request.getDate());
        booking.setReturnDate(request.getReturnDate());

        Booking savedBooking = bookingRepository.save(booking);

        // 2. Trigger automatic backend email notifications
        sendAdminEmail(savedBooking);

        return savedBooking;
    }

    private void sendAdminEmail(Booking booking) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("gselitedroptaxi@gmail.com"); // Put your admin email address here
            message.setSubject("🚗 New Taxi Booking Received! (#" + booking.getId() + ")");
            
            String emailBody = String.format(
                "A new booking has been made on GS Elite Drop Taxi:\n\n" +
                "Booking ID: #%d\n" +
                "Customer Name: %s\n" +
                "Mobile Number: %s\n" +
                "Pickup Location: %s\n" +
                "Drop-off Location: %s\n" +
                "Trip Type: %s\n" +
                "Pickup Date: %s\n" +
                "Return Date: %s\n\n" +
                "Please call the customer to confirm.",
                booking.getId(),
                booking.getName(),
                booking.getMobile(),
                booking.getPickup(),
                booking.getDropOff(),
                booking.getTripType(),
                booking.getDate(),
                (booking.getReturnDate() != null ? booking.getReturnDate() : "N/A")
            );

            message.setText(emailBody);
            mailSender.send(message);
            System.out.println("✅ Admin email notification sent successfully for Booking ID #" + booking.getId());

        } catch (Exception e) {
            System.err.println("❌ Failed to send admin email: " + e.getMessage());
        }
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}