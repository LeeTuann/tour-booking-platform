package com.booking.tourbookingplatform.repository;

import com.booking.tourbookingplatform.entity.BookingPassenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingPassengerRepository extends JpaRepository<BookingPassenger, Long> {
}
