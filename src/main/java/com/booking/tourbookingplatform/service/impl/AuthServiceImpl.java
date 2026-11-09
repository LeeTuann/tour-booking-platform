package com.booking.tourbookingplatform.service.impl;

import com.booking.tourbookingplatform.dto.request.LoginRequest;
import com.booking.tourbookingplatform.dto.response.AuthResponse;
import com.booking.tourbookingplatform.entity.User;
import com.booking.tourbookingplatform.exception.AppException;
import com.booking.tourbookingplatform.exception.ErrorCode;
import com.booking.tourbookingplatform.repository.UserRepository;
import com.booking.tourbookingplatform.security.JwtTokenProvider;
import com.booking.tourbookingplatform.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.identifier(), loginRequest.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByEmailOrPhone(loginRequest.identifier(), loginRequest.identifier())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        String token = jwtTokenProvider.generateToken(user.getEmail());

        return new AuthResponse(token, user.getEmail(), user.getRole());
    }
}
