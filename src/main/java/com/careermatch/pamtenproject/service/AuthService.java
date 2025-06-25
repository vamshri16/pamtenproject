package com.careermatch.pamtenproject.service;

import com.careermatch.pamtenproject.dto.LoginRequest;
import com.careermatch.pamtenproject.dto.LoginResponse;
import com.careermatch.pamtenproject.dto.SignupRequest;
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
            return "Email already in use.";
        }

        Role role = roleRepository.findByRoleName(request.getRoleName())
                .orElseThrow(() -> new RuntimeException("Role not found: " + request.getRoleName()));

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setFullName(request.getFullName());
        user.setRole(role);
        user.setActive(true);

        userRepository.save(user);
        return "User registered successfully";
    }

    public LoginResponse loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().getRoleName());



        user.setLastLoginTime(System.currentTimeMillis());
        userRepository.save(user);

        // Return the login response with token, role, and email

        return new LoginResponse(token, user.getRole().getRoleName(), user.getEmail());
    }
}