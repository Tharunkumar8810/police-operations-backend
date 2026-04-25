package com.example.Police.service;

import com.example.Police.dto.LoginRequest;
import com.example.Police.dto.LoginResponse;
import com.example.Police.dto.RegisterRequest;
import com.example.Police.dto.RegisterResponse;
import com.example.Police.model.Status;
import com.example.Police.model.User;
import com.example.Police.repository.UserRepository;
import com.example.Police.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered: " + request.getEmail());
        }

        if (userRepository.existsByBadgeNumber(request.getBadgeNumber())) {
            throw new RuntimeException("Badge number already in use: " + request.getBadgeNumber());
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .badgeNumber(request.getBadgeNumber())
                .role(request.getRole())
                .status(Status.ACTIVE)
                .build();

        User savedUser = userRepository.save(user);

        return RegisterResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .badgeNumber(savedUser.getBadgeNumber())
                .role(savedUser.getRole())
                .message("Police account created successfully.")
                .build();
    }

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(request.getEmail());
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            return new LoginResponse(token, user.getEmail(), user.getRole().name());
        }

        throw new RuntimeException("Invalid email or password");
    }
}