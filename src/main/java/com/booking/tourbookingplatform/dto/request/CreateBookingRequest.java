package com.booking.tourbookingplatform.dto.request;

import jakarta.validation.constraints.*;

import java.util.List;

public record CreateBookingRequest(
        @NotNull(message = "Chọn lịch khởi hành")
        Long scheduleId,
        @NotBlank(message = "Vui lòng nhập họ tên")
        String contactName,
        @Email(message = "Email không hợp lệ")
        @NotBlank(message = "Vui lòng nhập email")
        String contactEmail,
        @NotBlank(message = "Vui lòng nhập sdt")
        String contactPhone,
        @Min(value = 1, message = "Phải có ít nhất 1 người lớn")
        int totalAdults,
        @Min(value = 0, message = "Số lượng trẻ em không hợp lệ")
        int totalChildren,
        @NotEmpty(message = "Danh sách hành khách không được trống")
        List<PassengerRequest> passengers
) {
}
