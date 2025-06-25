package com.careermatch.pamtenproject.controller;

import com.careermatch.pamtenproject.dto.SignupRequest;
import com.careermatch.pamtenproject.dto.LoginRequest;
import com.careermatch.pamtenproject.dto.LoginResponse;
import com.careermatch.pamtenproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody SignupRequest request) {
        String result = authService.registerUser(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request) {
        LoginResponse response = authService.loginUser(request);
        return ResponseEntity.ok(response);
    }
}