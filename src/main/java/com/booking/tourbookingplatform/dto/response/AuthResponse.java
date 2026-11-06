package com.booking.tourbookingplatform.dto.response;

public record AuthResponse(
        String token,
        String email,
        String role
) {
}
