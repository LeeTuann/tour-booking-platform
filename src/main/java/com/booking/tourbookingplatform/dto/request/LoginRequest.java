package com.booking.tourbookingplatform.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Tài khoản (Email hoặc Sdt)")
        String identifier,
        @NotBlank(message = "Chưa nhập mật khẩu")
        String password
) {
}
