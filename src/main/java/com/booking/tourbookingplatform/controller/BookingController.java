package com.booking.tourbookingplatform.controller;

import com.booking.tourbookingplatform.dto.request.CreateBookingRequest;
import com.booking.tourbookingplatform.dto.response.ApiResponse;
import com.booking.tourbookingplatform.dto.response.BookingResponse;
import com.booking.tourbookingplatform.security.CustomUserDetails;
import com.booking.tourbookingplatform.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    @PostMapping
    public ResponseEntity<ApiResponse<BookingResponse>> createBooking(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                                      @Valid @RequestBody CreateBookingRequest request) {
        Long userId = currentUser.getUser().getId();
        BookingResponse response = bookingService.createBooking(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response,"Đã đặt chỗ, vui lòng thanh toán"));
    }
}
