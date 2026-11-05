package com.booking.tourbookingplatform.repository;

import com.booking.tourbookingplatform.entity.TourSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TourScheduleRepository extends JpaRepository<TourSchedule, Long> {
    @Modifying
    @Query("""
        UPDATE TourSchedule s 
        SET s.heldSeats = s.heldSeats + :seats
        WHERE s.id = :scheduleId
        AND (s.maxCapacity - (s.bookedSeats + s.heldSeats)) >= :seats
""")
    int holdSeatsAtomic(@Param("scheduleId") Long scheduleId, @Param("seats") int seats);
}
