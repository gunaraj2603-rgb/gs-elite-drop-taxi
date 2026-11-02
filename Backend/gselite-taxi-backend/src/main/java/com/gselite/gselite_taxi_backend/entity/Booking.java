package com.gselite.gselite_taxi_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String mobile;
    private String pickup;
    private String dropOff;
    private LocalDate date;
    private String tripType;
    private LocalDate returnDate;
    public Booking() {}

    public Booking(String name, String mobile, String pickup, String dropOff, LocalDate date, String tripType) {
        this.name = name;
        this.mobile = mobile;
        this.pickup = pickup;
        this.dropOff = dropOff;
        this.date = date;
        this.tripType = tripType;
        this.returnDate = returnDate;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getPickup() { return pickup; }
    public void setPickup(String pickup) { this.pickup = pickup; }

    public String getDropOff() { return dropOff; }
    public void setDropOff(String dropOff) { this.dropOff = dropOff; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getTripType() { return tripType; }
    public void setTripType(String tripType) { this.tripType = tripType; }

public LocalDate getReturnDate() { return returnDate; } // Resolves booking.getReturnDate()
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}