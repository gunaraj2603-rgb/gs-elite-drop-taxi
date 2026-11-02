package com.gselite.gselite_taxi_backend.dto;
import java.time.LocalDate;

public class BookingRequest {
    private String name;
    private String mobile;
    private String pickup;
    private String dropOff;
    private LocalDate date;
    private String tripType;
    private LocalDate returnDate;
    // Getters and Setters
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

    public LocalDate getReturnDate() { return returnDate;}
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate;    
     }

    }