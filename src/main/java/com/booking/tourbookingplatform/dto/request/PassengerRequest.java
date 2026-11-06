package com.booking.tourbookingplatform.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PassengerRequest(
        @NotBlank(message = "Họ tên không được để trống")
        String fullName,
        @NotBlank(message = "Loại hành khách không được để trống")
        String passengerType,
        String idCardNumber,
        @NotNull(message = "Ngày sinh không được trống")
        LocalDate dateOfBirth,
        String gender) {
}
