package com.booking.tourbookingplatform.dto.response;

import java.math.BigDecimal;

public record BookingResponse(
        String bookingCode,
        String status,
        BigDecimal totalAmount
) {
}
