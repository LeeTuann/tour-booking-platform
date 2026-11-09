package com.booking.tourbookingplatform.service.impl;

import com.booking.tourbookingplatform.dto.request.CreateBookingRequest;
import com.booking.tourbookingplatform.dto.response.BookingResponse;
import com.booking.tourbookingplatform.entity.Booking;
import com.booking.tourbookingplatform.entity.BookingPassenger;
import com.booking.tourbookingplatform.entity.TourSchedule;
import com.booking.tourbookingplatform.entity.User;
import com.booking.tourbookingplatform.exception.AppException;
import com.booking.tourbookingplatform.exception.ErrorCode;
import com.booking.tourbookingplatform.repository.BookingPassengerRepository;
import com.booking.tourbookingplatform.repository.BookingRepository;
import com.booking.tourbookingplatform.repository.TourScheduleRepository;
import com.booking.tourbookingplatform.repository.UserRepository;
import com.booking.tourbookingplatform.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final TourScheduleRepository scheduleRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final BookingPassengerRepository passengerRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BookingResponse createBooking(Long userId, CreateBookingRequest request) {
        int totalSeatsRequested = request.totalAdults() + request.totalChildren();

        int updatedRows = scheduleRepository.holdSeatsAtomic(request.scheduleId(), totalSeatsRequested);
        if(updatedRows ==0) {
            throw new AppException(ErrorCode.SEATS_NOT_AVAILABLE);
        }

        TourSchedule schedule = scheduleRepository.findById(request.scheduleId())
                .orElseThrow(() -> new AppException(ErrorCode.SCHEDULE_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        BigDecimal adultTotal = schedule.getAdultPrice().multiply(BigDecimal.valueOf(request.totalAdults()));
        BigDecimal childTotal = schedule.getChildPrice().multiply(BigDecimal.valueOf(request.totalChildren()));
        BigDecimal grandTotal = adultTotal.add(childTotal);

        Booking booking = Booking.builder()
                .bookingCode("BK"+ UUID.randomUUID().toString().substring(0, 8).toUpperCase())
                .user(user)
                .tourSchedule(schedule)
                .contactName(request.contactName())
                .contactEmail(request.contactEmail())
                .contactPhone(request.contactPhone())
                .totalAdults(request.totalAdults())
                .totalChildren(request.totalChildren())
                .totalAmount(grandTotal)
                .status("PENDING_PAYMENT")
                .paymentDeadline(Instant.now().plus(15, ChronoUnit.MINUTES))
                .build();
        bookingRepository.save(booking);

        List<BookingPassenger> passengers = request.passengers().stream().map(p -> BookingPassenger.builder()
                        .booking(booking)
                        .fullName(p.fullName())
                        .passengerType(p.passengerType())
                        .idCardNumber(p.idCardNumber())
                        .dateOfBirth(p.dateOfBirth())
                        .gender(p.gender())
                        .build()
                ).toList();
        passengerRepository.saveAll(passengers);
        return new BookingResponse(booking.getBookingCode(), booking.getStatus(), booking.getTotalAmount());

    }
}
