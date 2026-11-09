package com.booking.tourbookingplatform.service;

import com.booking.tourbookingplatform.dto.request.CreateBookingRequest;
import com.booking.tourbookingplatform.dto.response.BookingResponse;

public interface BookingService {
    BookingResponse createBooking(Long userId, CreateBookingRequest request);
}
