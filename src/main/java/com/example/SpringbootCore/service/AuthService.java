package com.example.SpringbootCore.service;

import com.example.SpringbootCore.dto.request.LoginRequest;
import com.example.SpringbootCore.dto.request.RegisterRequest;
import com.example.SpringbootCore.dto.response.AuthResponse;
import com.example.SpringbootCore.entity.User;
import com.example.SpringbootCore.exception.AppException;
import com.example.SpringbootCore.exception.ErrorCode;
import com.example.SpringbootCore.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthResponse login(LoginRequest request) {

        try {

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            String token = jwtUtil.generateToken(auth.getName());

            return new AuthResponse(token, auth.getName());

        } catch (AuthenticationException e) {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }
    }

    public AuthResponse register(RegisterRequest request) {

        User user = userService.register(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );

        String token = jwtUtil.generateToken(user.getUsername());

        return new AuthResponse(token, user.getUsername());
    }
}