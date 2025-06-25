package com.careermatch.pamtenproject.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String password;
    private String phone;
    private String roleName; // must match the `role_name` in `roles` table
    private String fullName; // ✅ Add this field
}