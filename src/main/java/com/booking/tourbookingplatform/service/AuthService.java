package com.booking.tourbookingplatform.service;

import com.booking.tourbookingplatform.dto.request.LoginRequest;
import com.booking.tourbookingplatform.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);
}
