package com.careermatch.pamtenproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private String email;
    private String fullName;
    private String phone;
    private String role;
}