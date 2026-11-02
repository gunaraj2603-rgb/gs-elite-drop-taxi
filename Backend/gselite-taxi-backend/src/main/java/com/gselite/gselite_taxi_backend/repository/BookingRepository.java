package com.gselite.gselite_taxi_backend.repository;

import com.gselite.gselite_taxi_backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Spring Data JPA automatically provides standard CRUD methods (save, findAll, etc.)
}