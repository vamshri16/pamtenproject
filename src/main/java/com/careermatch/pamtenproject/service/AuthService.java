package com.careermatch.pamtenproject.service;

import com.careermatch.pamtenproject.dto.*;
import com.careermatch.pamtenproject.model.Role;
import com.careermatch.pamtenproject.model.User;
import com.careermatch.pamtenproject.repository.RoleRepository;
import com.careermatch.pamtenproject.repository.UserRepository;
import com.careermatch.pamtenproject.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String registerUser(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return "User already exists with this email.";
        }

        Role role = roleRepository.findByRoleName(request.getRoleName())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(role)
                .fullName(request.getFullName())
                .build();

        userRepository.save(user);
        return "User registered successfully!";
    }

    public LoginResponse loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().getRoleName());
        return new LoginResponse(token, user.getRole().getRoleName(), user.getEmail());
    }
}