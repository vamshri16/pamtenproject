package com.careermatch.pamtenproject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "Role_id", referencedColumnName = "Role_id")
    private Role role;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Password_Hash", nullable = false)
    private String password;

    @Column(name = "Phone")
    private String phone;

    @Lob
    @Column(name = "QR_code")
    private byte[] qrCode;

    @Lob
    @Column(name = "Authenticator")
    private byte[] authenticator;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "active")
    private Boolean active; // ✅ Add this line

    public void setLastLoginTime(long l) {
    }

    public void setLastLoginTime(String lastLoginTime) {
        // This method is intentionally left blank.
        // It can be used to set the last login time if needed in the future.
    }
}