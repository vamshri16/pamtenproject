package com.careermatch.pamtenproject.controller;

import com.careermatch.pamtenproject.dto.UserResponse;
import com.careermatch.pamtenproject.model.User;
import com.careermatch.pamtenproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication) {
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse response = new UserResponse(
                user.getEmail(),
                user.getFullName(),
                user.getPhone(),
                user.getRole().getRoleName()
        );

        return ResponseEntity.ok(response);
    }
}