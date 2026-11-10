package com.booking.tourbookingplatform.controller;

import com.booking.tourbookingplatform.dto.request.LoginRequest;
import com.booking.tourbookingplatform.dto.response.ApiResponse;
import com.booking.tourbookingplatform.dto.response.AuthResponse;
import com.booking.tourbookingplatform.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest loginRequest)
    {
        AuthResponse authResponse = authService.login(loginRequest);
        return ResponseEntity.ok(ApiResponse.success(authResponse, "Đăng nhập thành công"));
    }
}
